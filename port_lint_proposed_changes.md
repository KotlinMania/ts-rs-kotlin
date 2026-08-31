# port-lint Proposed Changes

**Generated:** 2026-08-31
**Source:** tmp/ts-rs/src
**Target:** src/commonMain/kotlin/io/github/kotlinmania/tsrs

These are review proposals only. They are emitted when a Rust -> Kotlin pair matches only after fallback normalization, so the existing `port-lint` header is not an exact provenance match.

| Target file | Current header | Proposed header | Source path | Reason |
|-------------|----------------|-----------------|-------------|--------|
| `src/commonMain/kotlin/io/github/kotlinmania/tsrs/Chrono.kt` | `// port-lint: source ts-rs/src/chrono.rs` | `// port-lint: source chrono.rs` | `chrono.rs` | `port-lint provenance header matched only after fallback normalization: 'ts-rs/src/chrono.rs' vs expected 'chrono.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/tsrs/ChronoTest.kt` | `// port-lint: tests ts-rs/src/chrono.rs` | `// port-lint: tests chrono.rs` | `chrono.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:ts-rs/src/chrono.rs' vs expected 'chrono.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/tsrs/Ts.kt` | `// port-lint: source ts-rs/src/lib.rs` | `// port-lint: source lib.rs` | `lib.rs` | `port-lint provenance header matched only after fallback normalization: 'ts-rs/src/lib.rs' vs expected 'lib.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/tsrs/Dependency.kt` | `// port-lint: source ts-rs/src/lib.rs` | `// port-lint: source lib.rs` | `lib.rs` | `port-lint provenance header matched only after fallback normalization: 'ts-rs/src/lib.rs' vs expected 'lib.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/tsrs/TsTest.kt` | `// port-lint: tests ts-rs/src/lib.rs` | `// port-lint: tests lib.rs` | `lib.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:ts-rs/src/lib.rs' vs expected 'lib.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/tsrs/export/Export.kt` | `// port-lint: source ts-rs/src/export.rs` | `// port-lint: source export.rs` | `export.rs` | `port-lint provenance header matched only after fallback normalization: 'ts-rs/src/export.rs' vs expected 'export.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/tsrs/export/ExportTest.kt` | `// port-lint: tests ts-rs/src/export.rs` | `// port-lint: tests export.rs` | `export.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:ts-rs/src/export.rs' vs expected 'export.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/tsrs/export/Path.kt` | `// port-lint: source ts-rs/src/export/path.rs` | `// port-lint: source export/path.rs` | `export/path.rs` | `port-lint provenance header matched only after fallback normalization: 'ts-rs/src/export/path.rs' vs expected 'export/path.rs'` |
| `src/commonTest/kotlin/io/github/kotlinmania/tsrs/export/PathTest.kt` | `// port-lint: tests ts-rs/src/export/path.rs` | `// port-lint: tests export/path.rs` | `export/path.rs` | `port-lint provenance header matched only after fallback normalization: 'tests:ts-rs/src/export/path.rs' vs expected 'export/path.rs'` |
| `src/commonMain/kotlin/io/github/kotlinmania/tsrs/export/ExportError.kt` | `// port-lint: source ts-rs/src/export/error.rs` | `// port-lint: source export/error.rs` | `export/error.rs` | `port-lint provenance header matched only after fallback normalization: 'ts-rs/src/export/error.rs' vs expected 'export/error.rs'` |
