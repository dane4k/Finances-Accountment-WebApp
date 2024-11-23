package org.example.financesaccountmentwebapp.RestControllers;

import org.example.financesaccountmentwebapp.DTOS.CategoryDTO;
import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.models.User;
import org.example.financesaccountmentwebapp.repositories.CategoryRepository;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * роут для получения категорий по нику
     * @param username ник
     * @return список категорий
     */
    @GetMapping("/{username}")
    public ResponseEntity<List<CategoryDTO>> getCategories(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        List<Category> userCategories = categoryRepository.findByUser(user);
        List<Category> generalCategories = categoryRepository.findByUserIsNull();

        userCategories.addAll(generalCategories);

        List<CategoryDTO> categoryDTOs = userCategories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    /**
     * добавление категории привязанной к нику
     * @param username ник
     * @param categoryDTO модель категории
     * @return результат запроса
     */
    @PostMapping("/{username}")
    public ResponseEntity<String> addCategory(@PathVariable String username, @RequestBody CategoryDTO categoryDTO) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("Пользователь не найден", HttpStatus.NOT_FOUND);
        }

        Category category = new Category();
        category.setUser(user);
        category.setName(categoryDTO.getName());
        category.setIncome(categoryDTO.getIncome());
        categoryRepository.save(category);
        return new ResponseEntity<>("Категория добавлена", HttpStatus.CREATED);
    }

    /**
     * удаление категории
     * @param username ник
     * @param categoryName название категории
     * @return результат запроса
     */
    @DeleteMapping("/{username}/{categoryName}")
    public ResponseEntity<String> deleteCategory(@PathVariable String username, @PathVariable String categoryName) {
        User user = userRepository.findByUsername(username);
        Category category = categoryRepository.findByNameAndUser(categoryName, user).get(0);
        if (category.getUser() == user) {
            categoryRepository.delete(category);
            return new ResponseEntity<>("Категория удалена", HttpStatus.OK);
        }
        return new ResponseEntity<>("Вы не можете удалить общую категорию", HttpStatus.BAD_REQUEST);
    }


    /**
     * конверт модели в модель дто
     * @param category
     * @return
     */
    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setIncome(category.getIncome());

        return dto;
    }
}