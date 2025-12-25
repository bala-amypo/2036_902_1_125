public interface ProfitCalculationRecordRepository
        extends JpaRepository<ProfitCalculationRecord, Long> {

    List<ProfitCalculationRecord> findByMenuItemId(Long menuItemId);

    List<ProfitCalculationRecord>
            findByProfitMarginBetween(BigDecimal min, BigDecimal max);

    List<ProfitCalculationRecord>
            findByProfitMarginGreaterThanEqual(BigDecimal min);
}
