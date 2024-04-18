-- Opretter 'users' tabellen
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Opretter 'wishlists' tabellen
CREATE TABLE IF NOT EXISTS wishlists (
    wishlist_id INT AUTO_INCREMENT PRIMARY KEY,
    wishlist_name VARCHAR(255) NOT NULL,
    wishlist_deadline_date DATE NULL,
    wishlist_description TEXT,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Opretter 'items' tabellen
CREATE TABLE IF NOT EXISTS items (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(255) NOT NULL,
    item_price DECIMAL(10, 2),
    item_description TEXT,
    item_link VARCHAR(255),
    item_image VARCHAR(255),
    item_reserved_status BOOLEAN DEFAULT FALSE,
    reserved_name VARCHAR(255),
    wishlist_id INT NOT NULL,
    FOREIGN KEY (wishlist_id) REFERENCES wishlists(wishlist_id) ON DELETE CASCADE
);