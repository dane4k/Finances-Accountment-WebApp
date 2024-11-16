package org.example.financesaccountmentwebapp.dbService;

import org.example.financesaccountmentwebapp.models.Category;
import org.example.financesaccountmentwebapp.repositories.CategoryRepository;
import org.example.financesaccountmentwebapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createDefaultCategories();
    }

    private void createDefaultCategories() {
        if (categoryRepository.count() == 0) {
            Category category1 = new Category();
            category1.setName("Заработная плата");
            category1.setIncome(true);
            categoryRepository.save(category1);

            Category category2 = new Category();
            category2.setName("Дополнительный доход");
            category2.setIncome(true);
            categoryRepository.save(category2);

            Category category3 = new Category();
            category3.setName("Доход от инвестиций");
            category3.setIncome(true);
            categoryRepository.save(category3);

            Category category4 = new Category();
            category4.setName("Подарки и наследство");
            category4.setIncome(true);
            categoryRepository.save(category4);

            Category category5 = new Category();
            category5.setName("Социальные выплаты");
            category5.setIncome(true);
            categoryRepository.save(category5);

            Category category6 = new Category();
            category6.setName("Продажа имущества");
            category6.setIncome(true);
            categoryRepository.save(category6);


            Category category7 = new Category();
            category7.setName("Коммунальные услуги");
            category7.setIncome(false);
            categoryRepository.save(category7);

            Category category8 = new Category();
            category8.setName("Продукты питания");
            category8.setIncome(false);
            categoryRepository.save(category8);

            Category category9 = new Category();
            category9.setName("Транспорт");
            category9.setIncome(false);
            categoryRepository.save(category9);

            Category category10 = new Category();
            category10.setName("Аренда квартиры");
            category10.setIncome(false);
            categoryRepository.save(category10);

            Category category11 = new Category();
            category11.setName("Связь и интернет");
            category11.setIncome(false);
            categoryRepository.save(category11);

            Category category12 = new Category();
            category12.setName("Развлечения");
            category12.setIncome(false);
            categoryRepository.save(category12);

            Category category13 = new Category();
            category13.setName("Медицинские расходы");
            category13.setIncome(false);
            categoryRepository.save(category13);

            Category category14 = new Category();
            category14.setName("Одежда и обувь");
            category14.setIncome(false);
            categoryRepository.save(category14);

            Category category15 = new Category();
            category15.setName("Образование");
            category15.setIncome(false);
            categoryRepository.save(category15);

            Category category16 = new Category();
            category16.setName("Непредвиденные расходы");
            category16.setIncome(false);
            categoryRepository.save(category16);

            Category category17 = new Category();
            category17.setName("Крупные покупки");
            category17.setIncome(false);
            categoryRepository.save(category17);

            Category category18 = new Category();
            category18.setName("Прочие расходы");
            category18.setIncome(false);
            categoryRepository.save(category18);
        }
    }
}