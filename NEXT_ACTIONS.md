# Immediate Actions - High-Value Files

Based on AST analysis, here are the concrete next steps.

## Summary

- **Files Present:** 7/7 (100.0%)
- **Function parity:** 39/42 matched (target 163) — 92.9%
- **Class/type parity:** 7/13 matched (target 39) — 53.8%
- **Combined symbol parity:** 46/55 matched (target 202) — 83.6%
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

### 1. lib

- **Target:** `tsrs.Ts`
- **Similarity:** 0.15
- **Dependents:** 0
- **Priority Score:** 52808.5
- **Functions:** 18/20 matched (target 30)
- **Missing functions:** `visit`, `fmt`
- **Types:** 5/8 matched (target 6)
- **Missing types:** `Visit`, `TypeVisitor`, `IsOption`
- **Lint issues:** 2

### 2. export

- **Target:** `export.Export`
- **Similarity:** 0.48
- **Dependents:** 0
- **Priority Score:** 21505.2
- **Functions:** 13/14 matched (target 16)
- **Missing functions:** `visit`
- **Types:** 0/1 matched
- **Missing types:** `Visit`
- **Lint issues:** 1

### 3. chrono

- **Target:** `tsrs.Chrono`
- **Similarity:** 0.26
- **Dependents:** 0
- **Priority Score:** 20807.4
- **Functions:** 6/6 matched (target 39)
- **Missing functions:** _none_
- **Types:** 0/2 matched (target 12)
- **Missing types:** `WithoutGenerics`, `OptionInnerType`

### 4. export.path

- **Target:** `export.Path`
- **Similarity:** 0.37
- **Dependents:** 0
- **Priority Score:** 206.3
- **Functions:** 2/2 matched (target 11)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 1)
- **Missing types:** _none_

### 5. serde_json

- **Target:** `tsrs.SerdeJson [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 110.0
- **Functions:** 0/0 matched (target 37)
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 9)
- **Missing types:** _none_

### 6. export.error

- **Target:** `export.ExportError`
- **Similarity:** 1.00
- **Dependents:** 0
- **Priority Score:** 100.0
- **Functions:** 0/0 matched
- **Missing functions:** _none_
- **Types:** 1/1 matched (target 7)
- **Missing types:** _none_

### 7. tokio

- **Target:** `tsrs.Tokio [ZERO]`
- **Similarity:** 0.00
- **Dependents:** 0
- **Priority Score:** 10.0
- **Functions:** 0/0 matched (target 30)
- **Missing functions:** _none_
- **Types:** 0/0 matched (target 3)
- **Missing types:** _none_

## Success Criteria

For each file to be considered "complete":
- **Similarity ≥ 0.85** (Excellent threshold)
- All public APIs ported
- All tests ported
- Documentation ported
- port-lint header present

