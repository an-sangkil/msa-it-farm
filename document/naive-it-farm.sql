
/* Drop Tables */

DROP TABLE IF EXISTS address_detail;
DROP TABLE IF EXISTS anniversary;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS address_management;
DROP TABLE IF EXISTS calve_management;
DROP TABLE IF EXISTS common_code;
DROP TABLE IF EXISTS disease_treatment;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS group_member;
DROP TABLE IF EXISTS group_management;
DROP TABLE IF EXISTS hm_cattle_buy_info;
DROP TABLE IF EXISTS hm_cattle_sell_store_info;
DROP TABLE IF EXISTS house_keeping_book;
DROP TABLE IF EXISTS local_beff_management;
DROP TABLE IF EXISTS personal_code;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE address_detail
(
	-- 관리 번호
	management_number bigint NOT NULL,
	hm_ad_no bigint NOT NULL,
	hm_type varchar(12),
	hm_add_info varchar(128),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	PRIMARY KEY (management_number, hm_ad_no, user_id)
) WITHOUT OIDS;


CREATE TABLE address_management
(
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	-- 관리 번호
	management_number bigint NOT NULL,
	-- 주소록 사용자 이름
	user_name varchar(32),
	-- 메모
	memo text,
	PRIMARY KEY (user_id, management_number)
) WITHOUT OIDS;


CREATE TABLE anniversary
(
	-- 관리 번호
	hm_mg_no bigint NOT NULL,
	hm_am_no bigint NOT NULL,
	-- yyyyMMdd 형태
	hm_date timestamp,
	-- 생일/기념일/기타/직접 입력
	hm_type varchar(32),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	PRIMARY KEY (hm_mg_no, hm_am_no, user_id)
) WITHOUT OIDS;


CREATE TABLE calve_management
(
	-- 개체식별번호
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL,
	-- 순번
	seq smallint NOT NULL,
	-- 수정 회차
	fertilization_index smallint NOT NULL,
	-- 년도
	years varchar(4),
	-- 수정일자
	fertilization_date timestamp,
	-- 분만예정일
	expected_date_confinement timestamp,
	-- 실제 문만일
	calving_date timestamp NOT NULL,
	-- 정액번호
	sperm_no bigint,
	-- 특이사항
	nothing_special varchar(256),
	-- 개체식별번호(자식)
	entity_identification_number_child varchar(24),
	-- 개체관리번호(자식)
	entity_management_number_child varchar(24),
	PRIMARY KEY (entity_identification_number, entity_management_number, seq)
) WITHOUT OIDS;


CREATE TABLE common_code
(
	-- 코드
	code varchar(16) NOT NULL,
	-- 상위코드
	upper_code varchar(16),
	-- 그룹별 코드 순번
	code_seq bigint,
	-- 코드이름
	code_name varchar(128),
	-- 코드 설명
	code_comment varchar(2048),
	-- 생성일시
	created_time timestamp,
	PRIMARY KEY (code)
) WITHOUT OIDS;


CREATE TABLE disease_treatment
(
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL,
	-- 수정 회차
	fertilization_index smallint NOT NULL,
	-- 치료_날짜
	cure_date timestamp NOT NULL,
	-- 질병 명
	disease_name varchar(124) NOT NULL,
	-- 약재명
	medication_name varchar(64),
	-- 치료내역
	treatment_details varchar(512) NOT NULL,
	-- 투여방법(근육주사, 피하 주사 )
	injection_method varchar(256),
	-- 휴약 기간 만료일
	withdrawal_period_expiration_date timestamp,
	-- 기본 'N' N(미분실)/Y(분실)
	needle_lose_yn varchar(1) DEFAULT '''N''',
	PRIMARY KEY (entity_identification_number, entity_management_number, fertilization_index)
) WITHOUT OIDS;


CREATE TABLE email
(
	-- 관리 번호
	management_number bigint NOT NULL,
	hm_em_no bigint NOT NULL,
	hm_email varchar(32),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	PRIMARY KEY (management_number, hm_em_no, user_id)
) WITHOUT OIDS;


CREATE TABLE group_management
(
	-- 그룹번호
	group_no int NOT NULL,
	-- 그룹 이름
	group_name varchar(128),
	-- 그룹설명
	group_desc text,
	-- 그룹 생성일
	group_crt_time timestamp,
	PRIMARY KEY (group_no)
) WITHOUT OIDS;


CREATE TABLE group_member
(
	-- 그룹번호
	group_no int NOT NULL,
	-- 사용자 아이디
	user_id varchar(16) NOT NULL UNIQUE,
	PRIMARY KEY (group_no, user_id)
) WITHOUT OIDS;


CREATE TABLE hm_cattle_buy_info
(
	buy_store_name varchar(64),
	-- 
	-- 
	buy_date timestamp,
	buy_note varchar(512),
	buy_phone_number varchar(11),
	-- 구입 연락처 전화번호 2
	buy_sell_phone_number varchar(24),
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL,
	PRIMARY KEY (entity_identification_number, entity_management_number)
) WITHOUT OIDS;


CREATE TABLE hm_cattle_sell_store_info
(
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL,
	store_name varchar(256) NOT NULL,
	sell_date timestamp NOT NULL,
	sell_note varchar(512),
	sell_phone_number varchar(11),
	-- 판매가격
	selling_price int,
	-- 생체중(살아있는생물의무게)
	liveweight smallint,
	-- 도체중( 도살한 가축의 가죽, 머리, 발목, 내장 따위를 떼어 낸 나머지 몸뚱이의 체중.)
	dressed_weight smallint,
	-- 1++A, 1++B
	beef_grade varchar,
	PRIMARY KEY (entity_identification_number, entity_management_number)
) WITHOUT OIDS;


CREATE TABLE house_keeping_book
(
	-- 관리번호
	management_no varchar(128) NOT NULL,
	-- 수입/지출 카테고리
	category_code varchar(5) NOT NULL,
	business_day timestamp,
	amount bigint,
	breakdown varchar(256),
	-- 메모
	detail_contents varchar(256),
	PRIMARY KEY (management_no)
) WITHOUT OIDS;


CREATE TABLE local_beff_management
(
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL,
	entity_identification_number varchar(24) NOT NULL,
	parent_papa_no varchar(24),
	parent_mom_no varchar(24),
	-- 출생일, 태어난날
	-- 
	birth_day date,
	enter_date date,
	ear_tag_date timestamp,
	castration_date timestamp,
	-- Cow 암소  / Bull 수소
	gender varchar(4),
	-- Y(판매됨) /N(미판매)
	sell_yn varchar(1),
	-- 생성일시
	created_time timestamp,
	-- 수정시간
	modified_time timestamp,
	PRIMARY KEY (entity_management_number, entity_identification_number)
) WITHOUT OIDS;


CREATE TABLE personal_code
(
	code varchar(5) NOT NULL,
	-- 부모코드
	upper_code varchar(5) NOT NULL,
	-- 사용자 아이디
	user_id varchar(16),
	-- 순서
	code_seq int,
	-- 코드명
	code_name varchar(128),
	-- 코드 상세
	code_comments varchar(256),
	-- 생성일시
	created_time timestamp,
	PRIMARY KEY (code)
) WITHOUT OIDS;


CREATE TABLE phone
(
	-- 관리 번호
	management_number bigint NOT NULL,
	hm_pho_no bigint NOT NULL,
	phone_number varchar(12),
	type varchar(12),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	PRIMARY KEY (management_number, hm_pho_no, user_id)
) WITHOUT OIDS;


CREATE TABLE schedule
(
	-- 일정관리번호
	sch_mgt_no bigint NOT NULL,
	-- 같은날의 순번
	sch_seq bigint NOT NULL,
	-- 제목
	sch_subject varchar(512) NOT NULL,
	sch_content text,
	-- 기준날짜
	-- 
	std_date varchar(10) NOT NULL,
	-- 오늘 날씨 ('맑음......')
	today_weather_code varchar(12),
	min_degreespoint_code varchar(1),
	max_degreespoint_code varchar(1),
	-- 최저 기온
	-- 
	min_temperature bigint,
	-- 최고온도
	max_temperature bigint,
	-- 생성시간
	create_time timestamp,
	-- 수정시간
	modify_time timestamp,
	-- 공개 여부 'Y' : 공개  'N' 비공개
	public_yn varchar(1) DEFAULT 'N',
	-- 그룹번호
	group_no int NOT NULL,
	-- 사용자 아이디
	user_id varchar(16) NOT NULL UNIQUE,
	PRIMARY KEY (sch_mgt_no, sch_seq)
) WITHOUT OIDS;


CREATE TABLE users
(
	-- uuid
	-- 
	uuid varchar(64) NOT NULL,
	-- 사용자 아이디
	user_id varchar(16) NOT NULL UNIQUE,
	-- 패스워드
	password varchar(1024) NOT NULL,
	-- 사용자이름
	user_name varchar(32) NOT NULL,
	-- 이메일
	email varchar(32),
	-- 전화번호
	phone_number varchar(16),
	-- 휴대전화번호
	mobile_phone_number varchar(16),
	-- 주민번호
	ssn_number varchar(12),
	-- 성별(M : 남성F : 여성)
	gender varchar(1),
	-- 마지막 접속 시간
	last_login_time timestamp,
	-- 마지막 접속 실패 시간
	fail_login_time timestamp,
	-- 로그인 실패 횟수
	fail_login_count bigint,
	-- 잠금여부 (Y : 잠김)
	use_lock_state varchar(1) DEFAULT 'N',
	-- 비고
	description text,
	PRIMARY KEY (uuid)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE address_detail
	ADD FOREIGN KEY (management_number, user_id)
	REFERENCES address_management (management_number, user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE anniversary
	ADD FOREIGN KEY (hm_mg_no, user_id)
	REFERENCES address_management (management_number, user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE email
	ADD FOREIGN KEY (management_number, user_id)
	REFERENCES address_management (management_number, user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE phone
	ADD FOREIGN KEY (management_number, user_id)
	REFERENCES address_management (management_number, user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE common_code
	ADD FOREIGN KEY (upper_code)
	REFERENCES common_code (code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE group_member
	ADD FOREIGN KEY (group_no)
	REFERENCES group_management (group_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE schedule
	ADD FOREIGN KEY (group_no, user_id)
	REFERENCES group_member (group_no, user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE calve_management
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beff_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE disease_treatment
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beff_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_cattle_buy_info
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beff_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_cattle_sell_store_info
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beff_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE house_keeping_book
	ADD FOREIGN KEY (category_code)
	REFERENCES personal_code (code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE personal_code
	ADD FOREIGN KEY (upper_code)
	REFERENCES personal_code (code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

COMMENT ON COLUMN address_detail.management_number IS '관리 번호';
COMMENT ON COLUMN address_detail.user_id IS '사용자 아이디';
COMMENT ON COLUMN address_management.user_id IS '사용자 아이디';
COMMENT ON COLUMN address_management.management_number IS '관리 번호';
COMMENT ON COLUMN address_management.user_name IS '주소록 사용자 이름';
COMMENT ON COLUMN address_management.memo IS '메모';
COMMENT ON COLUMN anniversary.hm_mg_no IS '관리 번호';
COMMENT ON COLUMN anniversary.hm_date IS 'yyyyMMdd 형태';
COMMENT ON COLUMN anniversary.hm_type IS '생일/기념일/기타/직접 입력';
COMMENT ON COLUMN anniversary.user_id IS '사용자 아이디';
COMMENT ON COLUMN calve_management.entity_identification_number IS '개체식별번호';
COMMENT ON COLUMN calve_management.entity_management_number IS '개체관리번호';
COMMENT ON COLUMN calve_management.seq IS '순번';
COMMENT ON COLUMN calve_management.fertilization_index IS '수정 회차';
COMMENT ON COLUMN calve_management.years IS '년도';
COMMENT ON COLUMN calve_management.fertilization_date IS '수정일자';
COMMENT ON COLUMN calve_management.expected_date_confinement IS '분만예정일';
COMMENT ON COLUMN calve_management.calving_date IS '실제 문만일';
COMMENT ON COLUMN calve_management.sperm_no IS '정액번호';
COMMENT ON COLUMN calve_management.nothing_special IS '특이사항';
COMMENT ON COLUMN calve_management.entity_identification_number_child IS '개체식별번호(자식)';
COMMENT ON COLUMN calve_management.entity_management_number_child IS '개체관리번호(자식)';
COMMENT ON COLUMN common_code.code IS '코드';
COMMENT ON COLUMN common_code.upper_code IS '상위코드';
COMMENT ON COLUMN common_code.code_seq IS '그룹별 코드 순번';
COMMENT ON COLUMN common_code.code_name IS '코드이름';
COMMENT ON COLUMN common_code.code_comment IS '코드 설명';
COMMENT ON COLUMN common_code.created_time IS '생성일시';
COMMENT ON COLUMN disease_treatment.entity_management_number IS '개체관리번호';
COMMENT ON COLUMN disease_treatment.fertilization_index IS '수정 회차';
COMMENT ON COLUMN disease_treatment.cure_date IS '치료_날짜';
COMMENT ON COLUMN disease_treatment.disease_name IS '질병 명';
COMMENT ON COLUMN disease_treatment.medication_name IS '약재명';
COMMENT ON COLUMN disease_treatment.treatment_details IS '치료내역';
COMMENT ON COLUMN disease_treatment.injection_method IS '투여방법(근육주사, 피하 주사 )';
COMMENT ON COLUMN disease_treatment.withdrawal_period_expiration_date IS '휴약 기간 만료일';
COMMENT ON COLUMN disease_treatment.needle_lose_yn IS '기본 ''N'' N(미분실)/Y(분실)';
COMMENT ON COLUMN email.management_number IS '관리 번호';
COMMENT ON COLUMN email.user_id IS '사용자 아이디';
COMMENT ON COLUMN group_management.group_no IS '그룹번호';
COMMENT ON COLUMN group_management.group_name IS '그룹 이름';
COMMENT ON COLUMN group_management.group_desc IS '그룹설명';
COMMENT ON COLUMN group_management.group_crt_time IS '그룹 생성일';
COMMENT ON COLUMN group_member.group_no IS '그룹번호';
COMMENT ON COLUMN group_member.user_id IS '사용자 아이디';
COMMENT ON COLUMN hm_cattle_buy_info.buy_date IS '
';
COMMENT ON COLUMN hm_cattle_buy_info.buy_sell_phone_number IS '구입 연락처 전화번호 2';
COMMENT ON COLUMN hm_cattle_buy_info.entity_management_number IS '개체관리번호';
COMMENT ON COLUMN hm_cattle_sell_store_info.entity_management_number IS '개체관리번호';
COMMENT ON COLUMN hm_cattle_sell_store_info.selling_price IS '판매가격';
COMMENT ON COLUMN hm_cattle_sell_store_info.liveweight IS '생체중(살아있는생물의무게)';
COMMENT ON COLUMN hm_cattle_sell_store_info.dressed_weight IS '도체중( 도살한 가축의 가죽, 머리, 발목, 내장 따위를 떼어 낸 나머지 몸뚱이의 체중.)';
COMMENT ON COLUMN hm_cattle_sell_store_info.beef_grade IS '1++A, 1++B';
COMMENT ON COLUMN house_keeping_book.management_no IS '관리번호';
COMMENT ON COLUMN house_keeping_book.category_code IS '수입/지출 카테고리';
COMMENT ON COLUMN house_keeping_book.detail_contents IS '메모';
COMMENT ON COLUMN local_beff_management.entity_management_number IS '개체관리번호';
COMMENT ON COLUMN local_beff_management.birth_day IS '출생일, 태어난날
';
COMMENT ON COLUMN local_beff_management.gender IS 'Cow 암소  / Bull 수소';
COMMENT ON COLUMN local_beff_management.sell_yn IS 'Y(판매됨) /N(미판매)';
COMMENT ON COLUMN local_beff_management.created_time IS '생성일시';
COMMENT ON COLUMN local_beff_management.modified_time IS '수정시간';
COMMENT ON COLUMN personal_code.upper_code IS '부모코드';
COMMENT ON COLUMN personal_code.user_id IS '사용자 아이디';
COMMENT ON COLUMN personal_code.code_seq IS '순서';
COMMENT ON COLUMN personal_code.code_name IS '코드명';
COMMENT ON COLUMN personal_code.code_comments IS '코드 상세';
COMMENT ON COLUMN personal_code.created_time IS '생성일시';
COMMENT ON COLUMN phone.management_number IS '관리 번호';
COMMENT ON COLUMN phone.user_id IS '사용자 아이디';
COMMENT ON COLUMN schedule.sch_mgt_no IS '일정관리번호';
COMMENT ON COLUMN schedule.sch_seq IS '같은날의 순번';
COMMENT ON COLUMN schedule.sch_subject IS '제목';
COMMENT ON COLUMN schedule.std_date IS '기준날짜
';
COMMENT ON COLUMN schedule.today_weather_code IS '오늘 날씨 (''맑음......'')';
COMMENT ON COLUMN schedule.min_temperature IS '최저 기온
';
COMMENT ON COLUMN schedule.max_temperature IS '최고온도';
COMMENT ON COLUMN schedule.create_time IS '생성시간';
COMMENT ON COLUMN schedule.modify_time IS '수정시간';
COMMENT ON COLUMN schedule.public_yn IS '공개 여부 ''Y'' : 공개  ''N'' 비공개';
COMMENT ON COLUMN schedule.group_no IS '그룹번호';
COMMENT ON COLUMN schedule.user_id IS '사용자 아이디';
COMMENT ON COLUMN users.uuid IS 'uuid
';
COMMENT ON COLUMN users.user_id IS '사용자 아이디';
COMMENT ON COLUMN users.password IS '패스워드';
COMMENT ON COLUMN users.user_name IS '사용자이름';
COMMENT ON COLUMN users.email IS '이메일';
COMMENT ON COLUMN users.phone_number IS '전화번호';
COMMENT ON COLUMN users.mobile_phone_number IS '휴대전화번호';
COMMENT ON COLUMN users.ssn_number IS '주민번호';
COMMENT ON COLUMN users.gender IS '성별(M : 남성F : 여성)';
COMMENT ON COLUMN users.last_login_time IS '마지막 접속 시간';
COMMENT ON COLUMN users.fail_login_time IS '마지막 접속 실패 시간';
COMMENT ON COLUMN users.fail_login_count IS '로그인 실패 횟수';
COMMENT ON COLUMN users.use_lock_state IS '잠금여부 (Y : 잠김)';
COMMENT ON COLUMN users.description IS '비고';



