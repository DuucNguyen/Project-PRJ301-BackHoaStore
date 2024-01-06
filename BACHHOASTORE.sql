
--------------------------------
USE master
IF exists (SELECT NAME FROM master.dbo.sysdatabases WHERE NAME = 'BachHoaStore')
BEGIN
	DROP DATABASE [Assignment_PRJ301]
END

GO
	CREATE DATABASE [Assignment_PRJ301]

GO
	USE [Assignment_PRJ301]
GO
---------------------------------


-----------------------------CREATE AREA---------------------------
CREATE TABLE Category(
	CategoryID int primary key,
	CategoryName varchar(30) not null,
)
DROP table Category

create table Products(
	id int primary key,
	[name] nvarchar(50) not null,
	price money not null,
	categoryID int foreign key references Category(CategoryID),
	[description] nvarchar(100),
	imgLocation nvarchar(50) not null,
	storeQuantity int not null
)
DROP table products

CREATE TABLE accounts(
	username nvarchar(30) primary key,
	[password] nvarchar(30),
	fullName nvarchar(50),
	email nvarchar(50),
	[role] bit
)
DROP table accounts

CREATE table Cart(
	username nvarchar(30) references accounts(username),
	productID int foreign key references Products(id),
	numberOfProducts int not null
	PRIMARY KEY(
	username, productID
	)
)
Drop table Cart

CREATE table [Order](
	OrderID int IDENTITY(1,1) primary key,
	username nvarchar(30) references accounts(username),
	OrderDate date not null,
	ApprovedDate date,
	[Status] nvarchar(20) not null
)

CREATE table [OrderDetails](
	OrderID int references [Order](OrderID),
	ProductID int references Products(id),
	Quantity int not null,
	TotalPrice money not null,

	PRIMARY KEY(
	OrderID, ProductID
	)
)
drop table OrderDetails
DROP TABLE [ORDER]

----------------------INSERT AREA-------------------------
INSERT INTO accounts(username,[password],fullName, email, [role])
VALUES('admin','admin','admin','admin@admin.com',1)

INSERT INTO [Order] (username, OrderDate, [Status])
VALUES('admin','2023-10-22','not approced')
SELECT SCOPE_IDENTITY() as num;


INSERT INTO OrderDetails(ProductID, Quantity , Price)
VALUES(1,2,30000)
INSERT INTO OrderDetails(ProductID, Quantity , Price)
VALUES(2,2,30000)

INSERT INTO Category(CategoryID, CategoryName)
VALUES (1,'Drink'),
(2,'Food'),
(3,'Season'),
(4,'HouseHold Products'),
(5,'Otherss')

INSERT INTO Products(id,[name],price,categoryID,[description],imgLocation,storeQuantity)
VALUES(1,N'CocaCola',10000,1,'350ml',N'image/coca.webp',100),
(2,N'Pepsi',10000,1,'350ml',N'image/pepsi.webp',100),
(3,N'Fanta',10000,1,'350ml',N'image/fanta.webp',100),
(4,N'Spite',10000,1,'350ml',N'image/spite.jpg',100),
(5,N'Trà Ô Long',10000,1,'550ml',N'image/olong.webp',100),
(6,'Sting',10000,1,'350ml',N'image/sting.webp',100),
(7,N'Bia 333',12000,1,'500ml',N'image/bia_333.webp',100),
(8,N'Bia Tiger',13000,1,'500ml',N'image/tiger.webp',100),
(9,N'Bia Heneken',17000,1,'500ml',N'image/heneken.webp',100),
(10,'AquaFina',6000,1,'650ml',N'image/aqua.webp',100),
(11,'Snack O''Star',8000,2,N'Vị Tảo Biển',N'image/o''star.webp',100),
(12,'Snack Swing',8000,2,N'Vị BBQ',N'image/Swing.webp',100),
(13,'Snack CornChip',8000,2,N'Vị Bắp Nướng Bơ',N'image/cornChip.webp',100),
(14,N'OMO',250000,4,N'Bột Giặt 5,7 kg',N'image/botgiatOMO.webp',100),
(15,N'OMO',165000,4,N'Nước Giặt 1.9 lít',N'image/nuocgiatOMO.webp',100)



INSERT INTO Products(id,[name],price,categoryID,[description],imgLocation,storeQuantity)
VALUES(16,N'Tương Ớt Chinsu',12000,3,N'Chai 250G',N'image/tuongOtChinsu.webp',100),
(17,N'Nước Tương Chinsu',13000,3,N'Tỏi Ớt Chai 250ml',N'image/xidauChinsu.webp',100),
(19,N'Nước Mắm Nam Ngư',55000,3,N'Chai Thủy Tinh 500ml',N'image/namNguThuyTinh.webp',100),
(18,N'Tương Cà Chinsu',12000,3,N'Chai 250G',N'image/tuongCaChinsu.webp',100),
(20,N'Nước Mắm Chinsu',67000,3,N'Chai 720ml',N'image/ChinsuThuyTinh.webp',100),
(21,N'Hạt Nêm Chinsu',49000,3,N'Gói 900G',N'image/hatNemChinsu.webp',100),
(22,N'Dầu Đậu Nành Simply',67000,3,N'Chai 1 Lít',N'image/dauDauNanhSimply.webp',100),
(23,N'Dầu Ăn Neptune',69000,3,N'Chai 1 Lít',N'image/dauAnNeptune.webp',100),
(24,N'Vim Xanh Lá',30000,4,N'Chai 750ml',N'image/vimGreen.webp',100),
(25,N'Vim Xanh Lục',30000,4,N'Chai 750ml',N'image/vimBlue.webp',100),
(26,N'Nước Giặt D-NEE',205000,4,N'Can 3 lít',N'image/nuocgiatDnee.webp',100),
(27,N'Nước Tẩy Javel',15000,4,N'Chai 330ml',N'image/nuocTayJavel.webp',100),
(28,N'Dầu Gội Romano RED',95000,4,N'Chai 380G',N'image/dauGoiRomanoRed.webp',100),
(29,N'Dầu Gội Romano BLACK',95000,4,N'Chai 380G',N'image/dauGoiRomanoBlack.webp',100),
(30,N'Pin Eveready SW4 AAA',15000,5,N'Lốc 4 Viên',N'image/pinEveready.webp',100),
(31,N'Khẩu Trang 4 Lớp',45000,5,N'Hộp 50 cái',N'image/KhauTrang4lop.webp',100),
(32,N'Băng Dính',12000,5,N'Cuộn 5m',N'image/bangDinh.webp',100),
(33,N'Vở Ô Li',10000,5,N'80 Trang',N'image/voOli.webp',100)









------------------EXPERIMENT AREA-------------------------
SELECT *
FROM Products p JOIN Category c on p.categoryID = c.CategoryID


SELECT *
FROM Products p JOIN  Category c on p.categoryID=c.CategoryID
WHERE 

SELECT *
FROM Products p JOIN Category c on p.categoryID = c.CategoryID
WHERE id = 1

SELECT * 
FROM [Order] 
WHERE username = 'duc123'

UPDATE Cart 
SET numberOfProducts = numberOfProducts + 1
WHERE username	='admin' AND productID = 1 

SELECT * FROM [Order]


SELECT * 
FROM Cart c JOIN Products p on c.productID = p.id
JOIN Category cate on p.categoryID = cate.CategoryID
WHERE username = 'admin'


DELETE 
FROM Cart
WHERE username = 'admin' AND productID = 1


SELECT * 
FROM [OrderDetails] o JOIN Products p on o.productID = p.id


UPDATE [Order]
SET [Status] = 'Approved'
WHERE OrderID = 3

UPDATE Products
SET storeQuantity = storeQuantity - 1
Where id = 3

UPDATE Products
SET storeQuantity = storeQuantity + 1
Where id = 3


SELECT TOP 5 *
FROM Products p JOIN Category c on p.categoryID = c.CategoryID
ORDER BY storeQuantity ASC


INSERT INTO Products(id,[name],price,categoryID,[description],imgLocation,storeQuantity)
VALUES(34,N'testProduct',14000,3,N'test Description',N'image/images.png',100)

UPDATE Products
SET [name] = 'TEST',
price = 3,
categoryID = 1,
[description] = 'test',
imglocation = 'images.png',
storeQuantity = 100
WHERE id = 34

DELETE FROM Products
WHERE id = 34


------------------FIX AREA--------------------------------
UPDATE Category
SET CategoryName = 'HouseHold Products'
WHERE CategoryName ='Fashion'

UPDATE Category
SET CategoryName = 'Others'
WHERE CategoryName ='Things'

UPDATE accounts
SET email = '111111' , 
fullName = 'changed'
WHERE username = 'duc123' AND [password] = '123123'

UPDATE [Order]
SET ApprovedDate = ''
WHERE OrderID = ? 

UPDATE [Order]
SET [Status] = ''
WHERE OrderID = ? 

UPDATE Products
SET storeQuantity = storeQuantity - 
WHERE id = ? 
