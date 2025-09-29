# Blog REST API 

## Chức năng yêu cầu
1. Tạo REST API: GET, POST, PUT, DELETE cho resource `blogs` và quản lý `users`.
2. Xác thực & phân quyền dùng Spring Security + JWT.
   - User có trường `role` (ADMIN / USER).
   - Đăng ký, đăng nhập sinh JWT token.
   - USER chỉ được xem / tạo / sửa / xoá blog của chính mình.
   - ADMIN có toàn quyền (xem tất cả blog, xoá user, còn USER chỉ được xem/cập nhật blog của chính mình).

## Tài khoản seed mặc định
| Username | Password   | Role  |
|----------|------------|-------|
| admin    | admin123   | ADMIN |
| alice    | alice123   | USER  |


## Luồng xác thực
1. Đăng ký `POST /api/auth/register`
2. Đăng nhập `POST /api/auth/login` nhận JWT.
3. Gửi JWT ở header `Authorization: Bearer <token>` cho các request bảo vệ.

## TEST VỚI POSTMAN

### Đăng ký Admin (ADMIN REGISTER)
<img width="1919" height="1020" alt="image" src="https://github.com/user-attachments/assets/61f4c62f-2d0c-4e80-92c3-7e9b517cd6b1" />

### Đăng ký User (USER REGISTER)
<img width="1919" height="1020" alt="image" src="https://github.com/user-attachments/assets/af84d060-3093-4468-9f5b-8fe70eac7afc" />

### Đăng nhập Admin (ADMIN LOGIN)
<img width="1919" height="1022" alt="image" src="https://github.com/user-attachments/assets/ad51fe6b-c010-4218-9917-e01ee465d3e2" />

### Đăng nhập User (USER LOGIN)
<img width="1919" height="1018" alt="image" src="https://github.com/user-attachments/assets/971b7de1-d0e7-4315-bb17-4c48140e5606" />

### Get All Users (ADMIN)
<img width="1919" height="1015" alt="image" src="https://github.com/user-attachments/assets/e0fd0f37-11cd-446e-ad65-7a4e33308d63" />

### Get My Profile (USER)
<img width="1919" height="1021" alt="image" src="https://github.com/user-attachments/assets/8967a9f7-819f-4e49-8e2b-06b6734fee22" />

### Create Blog (USER)
<img width="1919" height="1016" alt="image" src="https://github.com/user-attachments/assets/c2a4ec11-89cb-4a38-9a79-1d30057bc8d0" />

### Get My Blogs (USER)
<img width="1919" height="1019" alt="image" src="https://github.com/user-attachments/assets/97488b17-9a2d-464c-bfac-4bffa9e20d76" />

### Get All Blogs (ADMIN)
<img width="1919" height="1023" alt="image" src="https://github.com/user-attachments/assets/9873be5f-d45f-48af-9f66-4136be2a83be" />

## TEST PHÂN QUYỀN

### USER cố xem tất cả các Blogs (lỗi 403)
Khi dùng "userToken" để gửi Request xem các Blogs -> nhận lại message "access denied"
<img width="1919" height="1018" alt="image" src="https://github.com/user-attachments/assets/36d9412d-d080-42b6-911f-1a86037cbedd" />


