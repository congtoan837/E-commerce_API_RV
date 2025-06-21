package com.poly.ex;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.poly.ex.content.ERole;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.poly.entity.Role;
import com.poly.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDataGenerator {
    static String[] VIETNAMESE_NAMES = {
        "Nguyễn Văn A", "Trần Thị B", "Lê Văn C", "Phạm Thị D", "Vũ Văn E",
        "Hoàng Thị F", "Ngô Văn G", "Phạm Văn H", "Đinh Thị I", "Lý Văn J"
    };

    static String[] ADDRESSES = {
        "Hà Nội", "TP. Hồ Chí Minh", "Đà Nẵng", "Cần Thơ", "Hải Phòng",
        "Nha Trang", "Vũng Tàu", "Huế", "Đà Lạt", "Vinh"
    };

    // Hàm tạo danh sách nhiều User mẫu
    public static Set<User> createSampleUsers() {
        Role userRole = Role.builder()
                .name(ERole.USER.getName())
                .permissions(ERole.USER.getPermissions())
                .build();

        Set<User> users = new HashSet<>();
        for (int i = 0; i < VIETNAMESE_NAMES.length; i++) {
            String name = VIETNAMESE_NAMES[i];
            String address = ADDRESSES[i];

            // Tạo email và số điện thoại giả lập từ tên và địa chỉ
            String email = createEmailFromName(name);
            String phone = "09" + (i + 1) + "3456789";

            // Tạo đối tượng User từ tên, địa chỉ và thông tin khác
            users.add(User.builder()
                    .name(name) // Tên từ mảng
                    .email(StringUtils.stripAccents(email)) // Email từ hàm tạo email
                    .phone(phone) // Số điện thoại giả lập
                    .address(address) // Địa chỉ từ mảng
                    .image("image" + (i + 1) + ".jpg") // Hình ảnh giả lập
                    .username("userexample" + i) // Tạo username
                    .password(passwordEncoder("123456")) // Mật khẩu giả lập
                    .verifyCode(RandomUtils.nextInt(1000, 9999)) // Mã xác minh
                    .isDeleted(false) // Trạng thái xoá
                    .roles(Set.of(userRole)) // Tạo roles
                    .build());
        }
        return users;
    }

    // Hàm tạo email từ tên
    private static String createEmailFromName(String name) {
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", ""); // Loại bỏ dấu tiếng Việt
        String emailName = normalized.toLowerCase().replaceAll(" ", "").replaceAll("[đĐ]", "d");
        return emailName + "@example.com";
    }

    private static String passwordEncoder(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
