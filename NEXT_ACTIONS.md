# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 7/7 (100.0%)
- **Function parity:** 42/42 matched (target 103) — 100.0%
- **Class/type parity:** 9/12 matched (target 31) — 75.0%
- **Combined symbol parity:** 51/54 matched (target 134) — 94.4%
- **Average inline-code cosine:** 0.36 (function body across 6 matched files)
- **Average documentation cosine:** 0.19 (doc text across 6 matched files)
- **Cheat-zeroed Files:** 1
- **Critical Issues:** 6 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. chrono

- **Target:** `tsrs.Chrono [PROVENANCE-FALLBACK]`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 20807.4
- **Functions:** 6/6 matched (target 40)
- **Missing functions:** _none_
- **Types:** 0/2 matched (target 13)
- **Missing types:** `WithoutGenerics`, `OptionInnerType`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ts-rs/src/chrono.rs` vs expected `chrono.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:ts-rs/src/chrono.rs` vs expected `chrono.rs`
- **Proposed provenance header:** `// port-lint: source chrono.rs` (current: `// port-lint: source ts-rs/src/chrono.rs`)
- **Proposed provenance header:** `// port-lint: tests chrono.rs` (current: `// port-lint: tests ts-rs/src/chrono.rs`)
- **Lint issues:** 2

### 2. lib

- **Target:** `tsrs.Ts [STUB] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 12810.0
- **Functions:** 20/20 matched (target 34)
- **Missing functions:** _none_
- **Types:** 7/8 matched
- **Missing types:** `TypeVisitor`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ts-rs/src/lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ts-rs/src/lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:ts-rs/src/lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source ts-rs/src/lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source ts-rs/src/lib.rs`)
- **Proposed provenance header:** `// port-lint: tests lib.rs` (current: `// port-lint: tests ts-rs/src/lib.rs`)
- **Lint issues:** 5

### 3. export

- **Target:** `export.Export [PROVENANCE-FALLBACK]`
- **Similarity:** 0.53
- **Dependents:** 0
- **Priority Score:** 1504.7
- **Functions:** 14/14 matched (target 18)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ts-rs/src/export.rs` vs expected `export.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:ts-rs/src/export.rs` vs expected `export.rs`
- **Proposed provenance header:** `// port-lint: source export.rs` (current: `// port-lint: source ts-rs/src/export.rs`)
- **Proposed provenance header:** `// port-lint: tests export.rs` (current: `// port-lint: tests ts-rs/src/export.rs`)
- **Lint issues:** 3

### 4. export.path

- **Target:** `export.Path [PROVENANCE-FALLBACK]`
- **Similarity:** 0.37
- **Dependents:** 0
- **Priority Score:** 206.3
- **Functions:** 2/2 matched (target 11)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ts-rs/src/export/path.rs` vs expected `export/path.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:ts-rs/src/export/path.rs` vs expected `export/path.rs`
- **Proposed provenance header:** `// port-lint: source export/path.rs` (current: `// port-lint: source ts-rs/src/export/path.rs`)
- **Proposed provenance header:** `// port-lint: tests export/path.rs` (current: `// port-lint: tests ts-rs/src/export/path.rs`)
- **Lint issues:** 2

### 5. export.error

- **Target:** `export.ExportError [PROVENANCE-FALLBACK]`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 100.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 7)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `ts-rs/src/export/error.rs` vs expected `export/error.rs`
- **Proposed provenance header:** `// port-lint: source export/error.rs` (current: `// port-lint: source ts-rs/src/export/error.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

## Reexport / Wiring Modules

These files match `reexport_modules` patterns in `.ast_distance_config.json`. They are filtered out of
normal priority and missing-file ladders because they are wiring
modules, not direct logic ports. Consult them for call-site routing;
do not treat them as the next implementation target by default.

### Matched

| Source | Target | Path |
|--------|--------|------|
| `serde_json` | `tsrs.SerdeJson` | `serde_json` |
| `tokio` | `tsrs.Tokio` | `tokio` |

