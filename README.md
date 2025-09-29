# Blog REST API
Chức năng yêu cầu
Tạo REST API: GET, POST, PUT, DELETE cho resource blogs và quản lý users.
Xác thực & phân quyền dùng Spring Security + JWT.
User có trường role (ADMIN / USER).
Đăng ký, đăng nhập sinh JWT token.
USER chỉ được xem / tạo / sửa / xoá blog của chính mình.
ADMIN có toàn quyền (xem tất cả blog, xoá user, còn USER chỉ được xem/cập nhật blog của chính mình).
