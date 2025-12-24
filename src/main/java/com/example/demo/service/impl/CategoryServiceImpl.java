@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category update(Long id, Category updated) {

        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        // ✅ PARTIAL SAFE UPDATE

        if (updated.getName() != null && !updated.getName().isBlank()) {
            existing.setName(updated.getName());
        }

        if (updated.getDescription() != null) {
            existing.setDescription(updated.getDescription());
        }

        if (updated.getActive() != null) {
            existing.setActive(updated.getActive());
        }

        return categoryRepository.save(existing);
    }
}
