# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 7/81 (8.6%)
- **Function parity:** 39/232 matched (target 163) — 16.8%
- **Class/type parity:** 7/341 matched (target 39) — 2.1%
- **Combined symbol parity:** 46/573 matched (target 202) — 8.0%
- **Average inline-code cosine:** 0.32 (function body across 7 matched files)
- **Average documentation cosine:** 0.25 (doc text across 7 matched files)
- **Cheat-zeroed Files:** 2
- **Critical Issues:** 6 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. export.path

- **Target:** `export.Path [PROVENANCE-FALLBACK]`
- **Similarity:** 0.37
- **Dependents:** 1
- **Priority Score:** 1000206.3
- **Functions:** 2/2 matched (target 11)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `export/path.rs` vs expected `export/path.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:export/path.rs` vs expected `export/path.rs`
- **Proposed provenance header:** `// port-lint: source export/path.rs` (current: `// port-lint: source export/path.rs`)
- **Proposed provenance header:** `// port-lint: tests export/path.rs` (current: `// port-lint: tests export/path.rs`)
- **Lint issues:** 2

### 2. export.error

- **Target:** `export.ExportError [PROVENANCE-FALLBACK]`
- **Similarity:** 1.00
- **Dependents:** 1
- **Priority Score:** 1000100.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 7)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `export/error.rs` vs expected `export/error.rs`
- **Proposed provenance header:** `// port-lint: source export/error.rs` (current: `// port-lint: source export/error.rs`)
- **Lint issues:** 1

### 3. lib

- **Target:** `tsrs.Ts [PROVENANCE-FALLBACK]`
- **Similarity:** 0.15
- **Dependents:** 0
- **Priority Score:** 52808.5
- **Functions:** 18/20 matched (target 30)
- **Missing functions:** `visit`, `fmt`
- **Types:** 5/8 matched (target 6)
- **Missing types:** `Visit`, `TypeVisitor`, `IsOption`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `lib.rs` vs expected `lib.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:lib.rs` vs expected `lib.rs`
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: source lib.rs` (current: `// port-lint: source lib.rs`)
- **Proposed provenance header:** `// port-lint: tests lib.rs` (current: `// port-lint: tests lib.rs`)
- **Lint issues:** 5

### 4. export

- **Target:** `export.Export [PROVENANCE-FALLBACK]`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 21505.2
- **Functions:** 13/14 matched (target 16)
- **Missing functions:** `visit`
- **Types:** 0/1 matched
- **Missing types:** `Visit`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `export.rs` vs expected `export.rs`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tests:export.rs` vs expected `export.rs`
- **Proposed provenance header:** `// port-lint: source export.rs` (current: `// port-lint: source export.rs`)
- **Proposed provenance header:** `// port-lint: tests export.rs` (current: `// port-lint: tests export.rs`)
- **Lint issues:** 3

### 5. chrono

- **Target:** `tsrs.Chrono [PROVENANCE-FALLBACK]`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 20807.4
- **Functions:** 6/6 matched (target 39)
- **Missing functions:** _none_
- **Types:** 0/2 matched (target 12)
- **Missing types:** `WithoutGenerics`, `OptionInnerType`
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `chrono.rs` vs expected `chrono.rs`
- **Proposed provenance header:** `// port-lint: source chrono.rs` (current: `// port-lint: source chrono.rs`)
- **Lint issues:** 1

### 6. serde_json

- **Target:** `tsrs.SerdeJson [ZERO] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 110.0
- **Functions:** 0/0 matched (target 37)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 9)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `serde_json.rs` vs expected `serde_json.rs`
- **Proposed provenance header:** `// port-lint: source serde_json.rs` (current: `// port-lint: source serde_json.rs`)
- **Lint issues:** 1

### 7. tokio

- **Target:** `tsrs.Tokio [ZERO] [PROVENANCE-FALLBACK]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 30)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 3)
- **Missing types:** _none_
- **Provenance warning:** port-lint provenance header matched only after fallback normalization: `tokio.rs` vs expected `tokio.rs`
- **Proposed provenance header:** `// port-lint: source tokio.rs` (current: `// port-lint: source tokio.rs`)
- **Lint issues:** 1

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

