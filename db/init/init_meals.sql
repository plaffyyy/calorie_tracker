CREATE TABLE meals (
   id SERIAL PRIMARY KEY,
   user_id INT NOT NULL,
   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);