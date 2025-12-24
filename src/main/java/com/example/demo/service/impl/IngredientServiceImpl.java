@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient update(Long id, Ingredient updated) {

        Ingredient existing = ingredientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found"));

        // ✅ PARTIAL SAFE UPDATE

        if (updated.getName() != null && !updated.getName().isBlank()) {
            existing.setName(updated.getName());
        }

        if (updated.getUnit() != null && !updated.getUnit().isBlank()) {
            existing.setUnit(updated.getUnit());
        }

        if (updated.getCostPerUnit() != null) {
            if (updated.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BadRequestException("Invalid cost per unit");
            }
            existing.setCostPerUnit(updated.getCostPerUnit());
        }

        if (updated.getActive() != null) {
            existing.setActive(updated.getActive());
        }

        return ingredientRepository.save(existing);
    }
}
