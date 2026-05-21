// port-lint: source src/export/error.rs
package io.github.kotlinmania.tsrs.export

/**
 * An error which may occur when exporting a type.
 *
 * Each subclass corresponds to one variant of the upstream `ExportError` enum;
 * the [message] string mirrors the upstream `#[error("...")]` attribute on that
 * variant. The `Formatting` subclass corresponds to the upstream `format`
 * feature; in this port the variant is always available, because Kotlin
 * Multiplatform has no equivalent of Cargo build-time feature gating.
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
