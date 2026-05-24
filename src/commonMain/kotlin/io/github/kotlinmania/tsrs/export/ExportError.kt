// port-lint: source src/export/error.rs
package io.github.kotlinmania.tsrs.export

/**
 * An error which may occur when exporting a type.
 *
 * Each subclass corresponds to one upstream export-error case. The [message]
 * string mirrors the text upstream reports for that case. The [Formatting]
 * subclass corresponds to upstream's optional formatting support; in this port
 * the variant is always available because Kotlin Multiplatform has no matching
 * build-time feature gate.
 */
public sealed class ExportError(
    message: String,
    cause: Throwable? = null,
) : Exception(message, cause) {

    public class CannotBeExported(public val typeName: String) :
        ExportError("this type cannot be exported")

    public class Formatting(public val detail: String) :
        ExportError("an error occurred while formatting the generated typescript output")

    public class Io(cause: Throwable) :
        ExportError("an error occurred while performing IO", cause)

    public object ManifestDirNotSet :
        ExportError("the environment variable CARGO_MANIFEST_DIR is not set")

    public class Fmt(cause: Throwable) :
        ExportError("an error occurred while writing to a formatted buffer", cause)

    public object InvalidImportExtension :
        ExportError("""TS_RS_IMPORT_EXTENSION must be either "js" or "ts"""")
}
