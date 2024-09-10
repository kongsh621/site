package com.example.project01.domain;

import lombok.Data;

;

@Data
// 메인 파일이라 public 있어야 한다
public class KakaoProfile {
    public Long id;
    public String connected_at;
    public Properties properties;
    public Kakao_Account kakao_account;

    @Data
    public class Properties {
        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }

    @Data
    public class Kakao_Account {
        public Boolean profile_nickname_needs_agreement;
        public Boolean profile_image_needs_agreement;
        public Profile profile;

        @Data
        public class Profile {
            public String nickname;
            public String thumbnail_image_url;
            public String profile_image_url;
            public Boolean is_default_image;
            public Boolean is_default_nickname;

        }
    }
}



