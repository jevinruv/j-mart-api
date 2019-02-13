INSERT INTO role (name)
VALUES
  ('ROLE_USER'),
  ('ROLE_ADMIN');

/*
INSERT INTO user (email, name, password, username)
VALUES
  ('jevinruv@gmail.com', 'Jevin', 'qwerty', 'jevinruv'),
  ('polo@gmail.com', 'Polo', 'qwerty', 'polo');

INSERT INTO user_roles (user_id, role_id)
VALUES
  (1, 1),
  (2, 2);
  */


INSERT INTO category (code, name)
VALUES
  ('computers', 'Computers'),
  ('phones', 'Phones'),
  ('electronics', 'Electronics');


INSERT INTO product (name, price, description, image_url, category)
VALUES
('Dell Inspiron 15 5000',
 80000,
 '5.6" Touchscreen Truelife LED-Backlit Full HD (1920 x 1080) Display; Intel Integrated 620 Graphics
8th Gen Intel Quad Core i5-8250U Processor (1.6GHz, up to 3.4GHz, 6MB Cache), beating i7-7500U in performance by an average of 26% according to tests from external websites
8 GB DDR4 SDRAM, 1TB SATA hard drive
Backlit Keyboard; 802.11 Dual Band Wireless-AC + Bluetooth 4.2, Integrated Widescreen HD Webcam',
 'https://images-na.ssl-images-amazon.com/images/I/51jaRdqhXrL._SX679_.jpg',
 'computers'),

('Xiaomi Redmi Note 5',
 30000,
 '3G HSDPA: 850 / 900 / 1900 / 2100 - 4G LTE: B1/2/3/4/5/7/8/20/28/38/40 - Hybrid Dual SIM (Nano-SIM, dual stand-by)
5.99 inches, 2160 x 1080 FHD+, 403 PPI - Corning Gorilla Glass
64GB + 4GB RAM - microSD, up to 256 GB (uses SIM 2 slot) - Snapdragon 636, octa-core - Fingerprint (rear-mounted) - Non-removable Li-Po 4000 mAh battery
Dual Rear Camera: 12 MP (f/2.2, 1.25µm) + 5 MP (f/2.0, 1.12µm) - 13 MP Front Camera, Selfie-Light',
 'https://images-na.ssl-images-amazon.com/images/I/51NmkGKj3TL._SX679_.jpg',
 'phones'),

('JBL Speaker',
 3000,
 'Bluetooth - Wirelessly connect up to 3 smartphones or tablets to the speaker and take turns playing powerful stereo sound.
Battery type : Lithium-ion Polymer (22.2Wh)2) 20 hours of playtime(varies by volume level and content)
IPX7 Waterproof',
 'https://images-na.ssl-images-amazon.com/images/I/61TSAn9KKxL._SX679_.jpg',
 'electronics');


INSERT INTO discount (percentage, product_id)
VALUES
  (25, 1),
  (35, 2);
