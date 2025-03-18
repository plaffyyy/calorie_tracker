CREATE TABLE meal_dishes (
     meal_id INT NOT NULL,
     dish_id INT NOT NULL,
     PRIMARY KEY (meal_id, dish_id),
     FOREIGN KEY (meal_id) REFERENCES meals(id) ON DELETE CASCADE,
     FOREIGN KEY (dish_id) REFERENCES dishes(id) ON DELETE CASCADE
);