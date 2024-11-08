-- --------------------------------------------------------
-- 학생 관련 데이터 생성
-- 생성 날짜: 2024-03-11
-- --------------------------------------------------------

-- 학생 기본 데이터
INSERT INTO 학생 (학생ID, 사용자ID, 학번, 이름, 학과ID, 입학년도, 학적상태) VALUES
-- 한국 학생
('STD001', 'USER001', '2024101001', '김민준', 'DEPT001', 2024, '재학'),
('STD002', 'USER002', '2024101002', '박지현', 'DEPT001', 2024, '재학'),
('STD003', 'USER003', '2024102001', '이서연', 'DEPT002', 2024, '재학'),
('STD007', 'USER012', '2023101001', '정도윤', 'DEPT001', 2023, '재학'),
('STD008', 'USER013', '2023102001', '강하은', 'DEPT002', 2023, '휴학'),
-- 외국인 학생
('STD004', 'USER004', '2024104001', 'John Smith', 'DEPT004', 2024, '재학'),
('STD005', 'USER005', '2024107001', 'Emma Wilson', 'DEPT007', 2024, '재학'),
('STD006', 'USER006', '2024102002', 'Liu Wei', 'DEPT002', 2024, '재학'),
('STD009', 'USER014', '2023104001', 'Sarah Johnson', 'DEPT004', 2023, '재학'),
('STD010', 'USER015', '2023107001', 'Chen Ming', 'DEPT007', 2023, '재학');

-- 학생 상세정보
INSERT INTO 학생상세정보 (학생ID, 주소, 전화번호, 생년월일, 성별, 학적상태, 프로필이미지URL) VALUES
-- 한국 학생
('STD001', '서울시 강남구', '010-1234-5678', '2005-03-15', '남성', '재학', '/profiles/std001.jpg'),
('STD002', '서울시 서초구', '010-2345-6789', '2005-05-20', '여성', '재학', '/profiles/std002.jpg'),
('STD003', '서울시 송파구', '010-3456-7890', '2005-07-25', '여성', '재학', '/profiles/std003.jpg'),
('STD007', '서울시 강동구', '010-8901-2345', '2004-04-12', '남성', '재학', '/profiles/std007.jpg'),
('STD008', '서울시 마포구', '010-9012-3456', '2004-08-30', '여성', '휴학', '/profiles/std008.jpg'),
-- 외국인 학생
('STD004', '서울시 용산구 Foreign Village', '010-4567-8901', '2004-08-10', '남성', '재학', '/profiles/std004.jpg'),
('STD005', '부산시 해운대구 Marine City', '010-5678-9012', '2005-01-25', '여성', '재학', '/profiles/std005.jpg'),
('STD006', '서울시 마포구', '010-6789-0123', '2005-11-30', '남성', '재학', '/profiles/std006.jpg'),
('STD009', '서울시 서대문구', '010-0123-4567', '2004-06-15', '여성', '재학', '/profiles/std009.jpg'),
('STD010', '인천시 연수구', '010-1234-5670', '2004-09-20', '남성', '재학', '/profiles/std010.jpg');

-- 휴학 신청 데이터
INSERT INTO 휴학신청 (휴학ID, 학생ID, 휴학유형, 시작일, 종료일, 사유, 상태, 신청일시) VALUES
                                                                ('LEAVE001', 'STD008', '일반휴학', '2024-03-02', '2025-02-28', '개인사유로 인한 휴학', '승인', '2024-02-01 10:00:00'),
                                                                ('LEAVE002', 'STD003', '군휴학', '2024-09-01', '2026-08-31', '군입대', '신청', '2024-03-10 14:30:00'),
                                                                ('LEAVE003', 'STD005', '질병휴학', '2024-03-02', '2024-08-31', '치료를 위한 휴학', '대기중', '2024-03-11 09:15:00');

-- 수강신청 장바구니 데이터
INSERT INTO 수강신청장바구니 (장바구니ID, 학생ID, 개설ID, 추가일시) VALUES
                                                    ('CART001', 'STD001', 'CLS001', '2024-02-20 10:00:00'),
                                                    ('CART002', 'STD001', 'CLS002', '2024-02-20 10:05:00'),
                                                    ('CART003', 'STD002', 'CLS001', '2024-02-20 11:00:00'),
                                                    ('CART004', 'STD004', 'CLS003', '2024-02-20 12:00:00'),
                                                    ('CART005', 'STD005', 'CLS004', '2024-02-20 13:00:00');

-- 강의평가 데이터
INSERT INTO 강의평가 (평가ID, 수강신청ID, 점수, 평가일시) VALUES
                                              ('EVAL001', 'REG001', 5, '2024-03-10 15:00:00'),
                                              ('EVAL002', 'REG002', 4, '2024-03-10 16:00:00'),
                                              ('EVAL003', 'REG003', 5, '2024-03-11 10:00:00'),
                                              ('EVAL004', 'REG004', 4, '2024-03-11 11:00:00'),
                                              ('EVAL005', 'REG005', 3, '2024-03-11 12:00:00');