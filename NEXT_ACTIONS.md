# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 4/7 (57.1%)
- **Function parity:** 22/22 matched (target 70) — 100.0%
- **Class/type parity:** 2/4 matched (target 23) — 50.0%
- **Combined symbol parity:** 24/26 matched (target 93) — 92.3%
- **Average inline-code cosine:** 0.63 (function body across 4 matched files)
- **Average documentation cosine:** 0.30 (doc text across 4 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 2 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. chrono

- **Target:** `tsrs.Chrono`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 20807.4
- **Functions:** 6/6 matched (target 40)
- **Missing functions:** _none_
- **Types:** 0/2 matched (target 13)
- **Missing types:** `WithoutGenerics`, `OptionInnerType`

### 2. export

- **Target:** `export.Export`
- **Similarity:** 0.52
- **Dependents:** 0
- **Priority Score:** 1504.8
- **Functions:** 14/14 matched (target 20)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_

### 3. export.path

- **Target:** `export.Path`
- **Similarity:** 0.75
- **Dependents:** 0
- **Priority Score:** 202.5
- **Functions:** 2/2 matched (target 10)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 4. export.error

- **Target:** `export.ExportError`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 100.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 7)
- **Missing types:** _none_

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

### Missing

| Source | Expected target | Deps | Source path | Expected path |
|--------|-----------------|------|-------------|---------------|
| `lib` | `Lib` | 0 | `lib.rs` | `Lib.kt` |
| `serde_json` | `SerdeJson` | 0 | `serde_json.rs` | `SerdeJson.kt` |
| `tokio` | `Tokio` | 0 | `tokio.rs` | `Tokio.kt` |

