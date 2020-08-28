SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS address_detail;
DROP TABLE IF EXISTS anniversary;
DROP TABLE IF EXISTS email;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS address_management;
DROP TABLE IF EXISTS calves_management;
DROP TABLE IF EXISTS cattle_buy_information;
DROP TABLE IF EXISTS cattle_sell_store_information;
DROP TABLE IF EXISTS common_code;
DROP TABLE IF EXISTS diary;
DROP TABLE IF EXISTS disease_treatment;
DROP TABLE IF EXISTS household_ledger;
DROP TABLE IF EXISTS oestrus;
DROP TABLE IF EXISTS vaccination;
DROP TABLE IF EXISTS local_beef_management;
DROP TABLE IF EXISTS personal_code;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE address_detail
(
	-- 관리 번호
	management_number bigint NOT NULL COMMENT '관리 번호',
	hm_ad_no bigint NOT NULL,
	hm_type varchar(12),
	hm_add_info varchar(128),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL COMMENT '사용자 아이디',
	PRIMARY KEY (management_number, hm_ad_no, user_id)
);


CREATE TABLE address_management
(
	-- 사용자 아이디
	user_id varchar(16) NOT NULL COMMENT '사용자 아이디',
	-- 관리 번호
	management_number bigint NOT NULL COMMENT '관리 번호',
	-- 주소록 사용자 이름
	user_name varchar(32) COMMENT '주소록 사용자 이름',
	-- 메모
	memo text COMMENT '메모',
	PRIMARY KEY (user_id, management_number)
);


CREATE TABLE anniversary
(
	-- 관리 번호
	hm_mg_no bigint NOT NULL COMMENT '관리 번호',
	hm_am_no bigint NOT NULL,
	-- 날짜
	day date COMMENT '날짜',
	-- 생일/기념일/기타/직접 입력
	hm_type varchar(32) COMMENT '생일/기념일/기타/직접 입력',
	-- 사용자 아이디
	user_id varchar(16) NOT NULL COMMENT '사용자 아이디',
	PRIMARY KEY (hm_mg_no, hm_am_no, user_id)
);


CREATE TABLE calves_management
(
	-- 개체식별번호
	entity_identification_number varchar(24) NOT NULL COMMENT '개체식별번호',
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	-- 순번
	seq smallint NOT NULL COMMENT '순번',
	-- 수정 회차
	fertilization_index smallint NOT NULL COMMENT '수정 회차',
	-- 년도
	years varchar(4) COMMENT '년도',
	-- 수정일자
	fertilization_date timestamp COMMENT '수정일자',
	-- 분만예정일
	expected_date_confinement timestamp COMMENT '분만예정일',
	-- 실제 문만일
	calving_date timestamp NOT NULL COMMENT '실제 문만일',
	-- 정액번호
	sperm_no varchar(24) COMMENT '정액번호',
	-- 특이사항
	nothing_special varchar(256) COMMENT '특이사항',
	-- 개체식별번호(자식)
	entity_identification_number_child varchar(24) COMMENT '개체식별번호(자식)',
	-- 개체관리번호(자식)
	entity_management_number_child varchar(24) COMMENT '개체관리번호(자식)',
	-- 삭제여부
	delete_yn boolean COMMENT '삭제여부',
	PRIMARY KEY (entity_identification_number, entity_management_number, seq)
);


CREATE TABLE cattle_buy_information
(
	buy_store_name varchar(64),
	-- 구입일자
	buy_date date COMMENT '구입일자',
	buy_note varchar(512),
	buy_phone_number varchar(11),
	-- 구입 연락처 전화번호 2
	buy_sell_phone_number varchar(24) COMMENT '구입 연락처 전화번호 2',
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	PRIMARY KEY (entity_identification_number, entity_management_number)
);


CREATE TABLE cattle_sell_store_information
(
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	store_name varchar(256) NOT NULL,
	sell_date date NOT NULL,
	sell_note varchar(512),
	sell_phone_number varchar(11),
	-- 판매가격
	selling_price int COMMENT '판매가격',
	-- 생체중(살아있는생물의무게)
	liveweight smallint COMMENT '생체중(살아있는생물의무게)',
	-- 도체중( 도살한 가축의 가죽, 머리, 발목, 내장 따위를 떼어 낸 나머지 몸뚱이의 체중.)
	dressed_weight smallint COMMENT '도체중( 도살한 가축의 가죽, 머리, 발목, 내장 따위를 떼어 낸 나머지 몸뚱이의 체중.)',
	-- 1++A, 1++B
	beef_grade varchar(0) COMMENT '1++A, 1++B',
	PRIMARY KEY (entity_identification_number, entity_management_number)
);


CREATE TABLE common_code
(
	-- 코드
	code varchar(16) NOT NULL COMMENT '코드',
	-- 상위코드
	upper_code varchar(16) COMMENT '상위코드',
	-- 그룹별 코드 순번
	code_seq smallint COMMENT '그룹별 코드 순번',
	-- 코드이름
	code_name varchar(128) COMMENT '코드이름',
	-- 코드 설명
	code_comment varchar(2048) COMMENT '코드 설명',
	-- 생성일시
	created_time timestamp COMMENT '생성일시',
	PRIMARY KEY (code)
);


CREATE TABLE diary
(
	-- uuid
	-- 
	uuid varchar(64) NOT NULL COMMENT 'uuid
',
	-- 같은날의 순번
	seq smallint NOT NULL COMMENT '같은날의 순번',
	-- 기준날짜
	-- 
	standard_date date NOT NULL COMMENT '기준날짜
',
	-- 제목
	subject varchar(512) NOT NULL COMMENT '제목',
	-- 오늘 한일
	today_content text COMMENT '오늘 한일',
	-- 내일 할일
	tomorrow_todo  text COMMENT '내일 할일',
	-- 오늘 날씨 ('맑음......')
	today_weather_code varchar(12) COMMENT '오늘 날씨 (''맑음......'')',
	-- 최저 기온
	-- 
	minimum_temperature smallint COMMENT '최저 기온
',
	-- 최대기온
	maximum_temperature smallint COMMENT '최대기온',
	-- 생성시간
	created_time timestamp COMMENT '생성시간',
	-- 수정시간
	modified_time timestamp COMMENT '수정시간',
	-- 공개 여부 'Y' : 공개  'N' 비공개
	public_yn varchar(1) DEFAULT 'N' COMMENT '공개 여부 ''Y'' : 공개  ''N'' 비공개',
	-- 사용자 아이디
	user_id varchar(16) COMMENT '사용자 아이디',
	PRIMARY KEY (uuid)
);


CREATE TABLE disease_treatment
(
	entity_identification_number varchar(24) NOT NULL,
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	-- 순번
	seq smallint NOT NULL COMMENT '순번',
	-- 치료_날짜
	cure_date date NOT NULL COMMENT '치료_날짜',
	-- 질병 명
	disease_name varchar(124) NOT NULL COMMENT '질병 명',
	-- 증상(증후)
	symptom varchar(248) COMMENT '증상(증후)',
	-- 치료내역(방법)
	treatment_details varchar(512) NOT NULL COMMENT '치료내역(방법)',
	-- 약재명
	medication_name varchar(64) COMMENT '약재명',
	-- 투여방법(근육주사, 피하 주사 )
	injection_method varchar(256) COMMENT '투여방법(근육주사, 피하 주사 )',
	-- 휴약 기간 만료일
	withdrawal_period_expiration_date date COMMENT '휴약 기간 만료일',
	-- 기본 'N' N(미분실)/Y(분실)
	needle_lose_yn varchar(1) DEFAULT 'N' COMMENT '기본 ''N'' N(미분실)/Y(분실)',
	-- 치료 당시의 개월수
	cure_number_of_month smallint COMMENT '치료 당시의 개월수',
	-- 생성일시
	created_time timestamp COMMENT '생성일시',
	-- 수정시간
	modified_time timestamp COMMENT '수정시간',
	PRIMARY KEY (entity_identification_number, entity_management_number, seq)
);


CREATE TABLE email
(
	-- 관리 번호
	management_number bigint NOT NULL COMMENT '관리 번호',
	hm_em_no bigint NOT NULL,
	hm_email varchar(32),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL COMMENT '사용자 아이디',
	PRIMARY KEY (management_number, hm_em_no, user_id)
);


CREATE TABLE household_ledger
(
	-- 관리번호
	management_no varchar(128) NOT NULL COMMENT '관리번호',
	-- 수입/지출 카테고리
	category_code varchar(5) NOT NULL COMMENT '수입/지출 카테고리',
	business_day timestamp,
	amount bigint,
	breakdown varchar(256),
	-- 메모
	detail_contents varchar(256) COMMENT '메모',
	PRIMARY KEY (management_no)
);


CREATE TABLE local_beef_management
(
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	entity_identification_number varchar(24) NOT NULL,
	parent_papa_no varchar(24),
	parent_mom_no varchar(24),
	-- 출생일, 태어난날
	-- 
	birth_day date COMMENT '출생일, 태어난날
',
	enter_date date,
	ear_tag_date timestamp,
	castration_date timestamp,
	-- Cow 암소  / Bull 수소
	gender varchar(4) COMMENT 'Cow 암소  / Bull 수소',
	-- Y(판매됨) /N(미판매)
	sell_yn varchar(1) COMMENT 'Y(판매됨) /N(미판매)',
	-- 삭제여부
	delete_yn boolean COMMENT '삭제여부',
	-- 사용자 아이디
	user_id varchar(16) COMMENT '사용자 아이디',
	-- 개월수 (팔렸을때 혹은 도축 기준)
	number_of_month smallint COMMENT '개월수 (팔렸을때 혹은 도축 기준)',
	-- 위치 
	location varchar(64) COMMENT '위치 ',
	-- 수정시간
	modified_time timestamp COMMENT '수정시간',
	-- 생성일시
	created_time timestamp COMMENT '생성일시',
	PRIMARY KEY (entity_management_number, entity_identification_number)
);


CREATE TABLE oestrus
(
	-- 순번
	seq smallint NOT NULL COMMENT '순번',
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	entity_identification_number varchar(24) NOT NULL,
	-- 발정일시(24시)
	oestus_datetime timestamp COMMENT '발정일시(24시)',
	-- 증상(증후)
	symptom varchar(248) COMMENT '증상(증후)',
	PRIMARY KEY (seq, entity_management_number)
);


CREATE TABLE personal_code
(
	code varchar(5) NOT NULL,
	-- 부모코드
	upper_code varchar(5) NOT NULL COMMENT '부모코드',
	-- 사용자 아이디
	user_id varchar(16) COMMENT '사용자 아이디',
	-- 순서
	code_seq int COMMENT '순서',
	-- 코드명
	code_name varchar(128) COMMENT '코드명',
	-- 코드 상세
	code_comments varchar(256) COMMENT '코드 상세',
	-- 생성일시
	created_time timestamp COMMENT '생성일시',
	PRIMARY KEY (code)
);


CREATE TABLE phone
(
	-- 관리 번호
	management_number bigint NOT NULL COMMENT '관리 번호',
	hm_pho_no bigint NOT NULL,
	phone_number varchar(12),
	type varchar(12),
	-- 사용자 아이디
	user_id varchar(16) NOT NULL COMMENT '사용자 아이디',
	PRIMARY KEY (management_number, hm_pho_no, user_id)
);


CREATE TABLE users
(
	-- uuid
	-- 
	uuid varchar(64) NOT NULL COMMENT 'uuid
',
	-- 사용자 아이디
	user_id varchar(16) NOT NULL COMMENT '사용자 아이디',
	-- 패스워드
	password varchar(1024) NOT NULL COMMENT '패스워드',
	-- 사용자이름
	user_name varchar(32) NOT NULL COMMENT '사용자이름',
	-- 이메일
	email varchar(32) COMMENT '이메일',
	-- 전화번호
	phone_number varchar(16) COMMENT '전화번호',
	-- 휴대전화번호
	mobile_phone_number varchar(16) COMMENT '휴대전화번호',
	-- 주민번호
	ssn_number varchar(12) COMMENT '주민번호',
	-- 성별(M : 남성F : 여성)
	gender varchar(1) COMMENT '성별(M : 남성F : 여성)',
	-- 마지막 접속 시간
	last_login_time timestamp COMMENT '마지막 접속 시간',
	-- 마지막 접속 실패 시간
	fail_login_time timestamp COMMENT '마지막 접속 실패 시간',
	-- 로그인 실패 횟수
	fail_login_count bigint COMMENT '로그인 실패 횟수',
	-- 잠금여부 (Y : 잠김)
	use_lock_state varchar(1) DEFAULT 'N' COMMENT '잠금여부 (Y : 잠김)',
	-- 비고
	description varchar(256) COMMENT '비고',
	PRIMARY KEY (uuid),
	UNIQUE (user_id)
);


CREATE TABLE vaccination
(
	-- 개체관리번호
	entity_management_number varchar(24) NOT NULL COMMENT '개체관리번호',
	entity_identification_number varchar(24) NOT NULL,
	-- 날짜
	day date NOT NULL COMMENT '날짜',
	-- 예방접종 종류
	vaccination_type varbinary(32) COMMENT '예방접종 종류',
	PRIMARY KEY (entity_management_number, entity_identification_number, day)
);



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


ALTER TABLE calves_management
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beef_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE cattle_buy_information
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beef_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE cattle_sell_store_information
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beef_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE disease_treatment
	ADD FOREIGN KEY (entity_identification_number, entity_management_number)
	REFERENCES local_beef_management (entity_identification_number, entity_management_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE oestrus
	ADD FOREIGN KEY (entity_management_number, entity_identification_number)
	REFERENCES local_beef_management (entity_management_number, entity_identification_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE vaccination
	ADD FOREIGN KEY (entity_management_number, entity_identification_number)
	REFERENCES local_beef_management (entity_management_number, entity_identification_number)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE household_ledger
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



