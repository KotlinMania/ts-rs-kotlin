import Testing
import TsRs

// Smoke test for the Kotlin → Swift Export → SPM → swift test pipeline.
@Suite("TsRs Swift Export Smoke Tests")
struct TsRsExportTests {
    @Test("TsRs swift module imported cleanly")
    func testSwiftModuleLoads() {
        #expect(Bool(true))
    }
}
