const jsonServer = require('json-server');
const jwt = require('jsonwebtoken');

const server = jsonServer.create();

const middlewares = jsonServer.defaults();

const PORT = process.env.PORT || 3000;
const router = jsonServer.router('./db.json')
server.use(router)

// Các route xử lý thao tác với các bảng trong cơ sở dữ liệu
const usersRoutes = require('./routes/users');
const productsRoutes = require('./routes/products');
const categoriesRoutes = require('./routes/categories');
const manufacturersRoutes = require('./routes/manufacturers');
const ordersRoutes = require('./routes/orders');
const reviewsRoutes = require('./routes/reviews');

const SECRET_KEY = '123456789'
const expiresIn = '1h'


function verifyToken(token) {
  return jwt.verify(
    token,
    SECRET_KEY,
    (err, decode) => decode !== undefined ? decode : err)
}

function isAuthenticated({ email, password }) {
  return db.users.findIndex(user => user.email === email && user.password === password) !== -1
}

// Sử dụng middleware để parse body của request thành dạng json
server.use(jsonServer.bodyParser);

server.use(middlewares);

// Sử dụng các route tương ứng với các bảng
server.use('/api/users', usersRoutes);
server.use('/api/products', productsRoutes);
server.use('/api/categories', categoriesRoutes);
server.use('/api/manufacturers', manufacturersRoutes);
server.use('/api/orders', ordersRoutes);
server.use('/api/reviews', reviewsRoutes);

// Khởi chạy server
server.listen(PORT, () => {
  console.log(`Server đang lắng nghe ở cổng ${PORT}...`);
});
