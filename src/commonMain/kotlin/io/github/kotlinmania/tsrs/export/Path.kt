// port-lint: source export/path.rs
package io.github.kotlinmania.tsrs.export

private const val ERROR_MESSAGE = """The path provided with `@Ts(exportTo = "..")` is not valid"""
private const val ROOT = "/"

internal fun absolute(path: String, currentDirectory: String = "."): Result<String> {
    val fullPath = joinPath(currentDirectory, path)
    val out = mutableListOf<String>()

    for (comp in fullPath.components()) {
        when (comp) {
            "." -> Unit
            ".." -> {
                if (out.isEmpty()) {
                    return Result.failure(ExportError.CannotBeExported(ERROR_MESSAGE))
                }
                out.removeAt(out.lastIndex)
            }
            else -> out.add(comp)
        }
    }

    return Result.success(
        if (out.isNotEmpty()) {
            out.toPathString()
        } else {
            "."
        },
    )
}

internal fun diffPaths(path: String, base: String, currentDirectory: String = "."): Result<String> {
    val pathResolved = absolute(path, currentDirectory).getOrElse { return Result.failure(it) }
    val baseResolved = absolute(base, currentDirectory).getOrElse { return Result.failure(it) }

    val ita = pathResolved.components().iterator()
    val itb = baseResolved.components().iterator()
    val comps = mutableListOf<String>()

    var nextA: String? = if (ita.hasNext()) ita.next() else null
    var nextB: String? = if (itb.hasNext()) itb.next() else null

    while (true) {
        val a = nextA
        val b = nextB
        when {
            a == "." || a == ".." || b == "." || b == ".." ->
                error("The paths have been cleaned, no '.' or '..' components are present")
            a == null && b == null -> break
            a != null && b == null -> {
                comps.add(a)
                while (ita.hasNext()) comps.add(ita.next())
                break
            }
            a == null -> {
                comps.add("..")
                nextB = if (itb.hasNext()) itb.next() else null
            }
            comps.isEmpty() && a == b -> {
                nextA = if (ita.hasNext()) ita.next() else null
                nextB = if (itb.hasNext()) itb.next() else null
            }
            else -> {
                comps.add("..")
                while (itb.hasNext()) {
                    itb.next()
                    comps.add("..")
                }
                comps.add(a)
                while (ita.hasNext()) comps.add(ita.next())
                break
            }
        }
    }

    return Result.success(comps.toPathString())
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
