# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 7/7 (100.0%)
- **Function parity:** 42/42 matched (target 104) — 100.0%
- **Class/type parity:** 12/12 matched (target 34) — 100.0%
- **Combined symbol parity:** 54/54 matched (target 138) — 100.0%
- **Average inline-code cosine:** 0.33 (function body across 7 matched files)
- **Average documentation cosine:** 0.28 (doc text across 7 matched files)
- **Cheat-zeroed Files:** 0
- **Critical Issues:** 6 files with <0.60 function similarity

## Priority 1: Fix Incomplete High-Dependency Files

No incomplete high-dependency files detected.

## Priority 2: Port Missing High-Value Files

Critical missing files (>10 dependencies):

No missing high-value files detected.

## Detailed Work Items

Every matched file is listed below with function and type symbol parity.

### 1. lib

- **Target:** `tsrs.Ts`
- **Similarity:** 0.17
- **Dependents:** 0
- **Priority Score:** 2808.3
- **Functions:** 20/20 matched (target 34)
- **Missing functions:** _none_
- **Types:** 8/8 matched (target 9)
- **Missing types:** _none_

### 2. export

- **Target:** `export.Export`
- **Similarity:** 0.52
- **Dependents:** 0
- **Priority Score:** 1504.8
- **Functions:** 14/14 matched (target 19)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 2)
- **Missing types:** _none_

### 3. chrono

- **Target:** `tsrs.Chrono`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 807.4
- **Functions:** 6/6 matched (target 40)
- **Missing functions:** _none_
- **Types:** 2/2 matched (target 15)
- **Missing types:** _none_

### 4. export.path

- **Target:** `export.Path`
- **Similarity:** 0.37
- **Dependents:** 0
- **Priority Score:** 206.3
- **Functions:** 2/2 matched (target 11)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. export.error

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

### Matched

| Source | Target | Path |
|--------|--------|------|
| `serde_json` | `tsrs.SerdeJson` | `serde_json` |
| `tokio` | `tsrs.Tokio` | `tokio` |

