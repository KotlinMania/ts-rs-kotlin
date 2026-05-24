// port-lint: source src/export/path.rs
package io.github.kotlinmania.tsrs.export

private const val ERROR_MESSAGE = """The path provided with `@Ts(exportTo = "..")` is not valid"""
private const val ROOT = "/"

internal fun absolute(path: String, currentDirectory: String = "."): Result<String> =
    clean(joinPath(currentDirectory, path))

internal fun diffPaths(path: String, base: String, currentDirectory: String = "."): Result<String> {
    val pathComponents = absolute(path, currentDirectory).getOrElse { return Result.failure(it) }.components()
    val baseComponents = absolute(base, currentDirectory).getOrElse { return Result.failure(it) }.components()

    val output = mutableListOf<String>()
    var pathIndex = 0
    var baseIndex = 0

    while (true) {
        val pathComponent = pathComponents.getOrNull(pathIndex)
        val baseComponent = baseComponents.getOrNull(baseIndex)

        when {
            pathComponent == "." || pathComponent == ".." || baseComponent == "." || baseComponent == ".." ->
                error("The paths have been cleaned, so no '.' or '..' components are present")
            pathComponent == null && baseComponent == null ->
                return Result.success(output.toPathString())
            pathComponent != null && baseComponent == null -> {
                output += pathComponents.drop(pathIndex)
                return Result.success(output.toPathString())
            }
            pathComponent == null -> {
                output += ".."
                baseIndex += 1
            }
            output.isEmpty() && pathComponent == baseComponent -> {
                pathIndex += 1
                baseIndex += 1
            }
            else -> {
                output += ".."
                output += baseComponents.drop(baseIndex + 1).map { ".." }
                output += pathComponent
                output += pathComponents.drop(pathIndex + 1)
                return Result.success(output.toPathString())
            }
        }
    }
}

private fun clean(path: String): Result<String> {
    val output = mutableListOf<String>()

    for (component in path.components()) {
        when (component) {
            "." -> Unit
            ".." -> {
                if (output.isEmpty()) {
                    return Result.failure(ExportError.CannotBeExported(ERROR_MESSAGE))
                }
                output.removeAt(output.lastIndex)
            }
            else -> output += component
        }
    }

    return Result.success(
        if (output.isNotEmpty()) {
            output.toPathString()
        } else {
            "."
        },
    )
}

private fun joinPath(base: String, path: String): String =
    if (path.isRooted()) {
        path
    } else {
        "${base.trimEnd('/')}/$path"
    }

private fun String.isRooted(): Boolean =
    startsWith(ROOT) || matches(Regex("^[A-Za-z]:[/\\\\].*"))

private fun String.components(): List<String> {
    val normalized = replace('\\', '/')
    val output = mutableListOf<String>()
    var rest = normalized

    val drivePrefix = Regex("^[A-Za-z]:").find(rest)?.value
    if (drivePrefix != null) {
        output += drivePrefix
        rest = rest.removePrefix(drivePrefix)
    }

    if (rest.startsWith(ROOT)) {
        output += ROOT
        rest = rest.trimStart('/')
    }

    output += rest.split('/').filter { it.isNotEmpty() }
    return output
}

private fun List<String>.toPathString(): String {
    if (isEmpty()) {
        return ""
    }

    val first = first()
    val rest = drop(1)
    return when {
        first == ROOT -> ROOT + rest.joinToString("/")
        first.endsWith(":") -> first + if (rest.isEmpty()) "/" else "/" + rest.joinToString("/")
        else -> joinToString("/")
    }
}
