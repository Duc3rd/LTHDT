--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-11-16 21:54:52

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- TOC entry 4913 (class 1262 OID 5)
-- Name: postgres; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE postgres OWNER TO postgres;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4914 (class 0 OID 0)
-- Dependencies: 4913
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- TOC entry 7 (class 2615 OID 24866)
-- Name: lthdtbtl; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA lthdtbtl;


ALTER SCHEMA lthdtbtl OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 238 (class 1259 OID 41328)
-- Name: certificate; Type: TABLE; Schema: lthdtbtl; Owner: postgres
--

CREATE TABLE lthdtbtl.certificate (
    id integer NOT NULL,
    student_id integer,
    class_id integer,
    status character varying(10)
);


ALTER TABLE lthdtbtl.certificate OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 41327)
-- Name: certificate_id_seq; Type: SEQUENCE; Schema: lthdtbtl; Owner: postgres
--

CREATE SEQUENCE lthdtbtl.certificate_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE lthdtbtl.certificate_id_seq OWNER TO postgres;

--
-- TOC entry 4915 (class 0 OID 0)
-- Dependencies: 237
-- Name: certificate_id_seq; Type: SEQUENCE OWNED BY; Schema: lthdtbtl; Owner: postgres
--

ALTER SEQUENCE lthdtbtl.certificate_id_seq OWNED BY lthdtbtl.certificate.id;


--
-- TOC entry 236 (class 1259 OID 41302)
-- Name: class; Type: TABLE; Schema: lthdtbtl; Owner: postgres
--

CREATE TABLE lthdtbtl.class (
    id integer NOT NULL,
    name character varying(100),
    start_date date,
    end_date date,
    description text
);


ALTER TABLE lthdtbtl.class OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 41301)
-- Name: class_id_seq; Type: SEQUENCE; Schema: lthdtbtl; Owner: postgres
--

CREATE SEQUENCE lthdtbtl.class_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE lthdtbtl.class_id_seq OWNER TO postgres;

--
-- TOC entry 4916 (class 0 OID 0)
-- Dependencies: 235
-- Name: class_id_seq; Type: SEQUENCE OWNED BY; Schema: lthdtbtl; Owner: postgres
--

ALTER SEQUENCE lthdtbtl.class_id_seq OWNED BY lthdtbtl.class.id;


--
-- TOC entry 232 (class 1259 OID 24909)
-- Name: provinces; Type: TABLE; Schema: lthdtbtl; Owner: postgres
--

CREATE TABLE lthdtbtl.provinces (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE lthdtbtl.provinces OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24908)
-- Name: provinces_id_seq; Type: SEQUENCE; Schema: lthdtbtl; Owner: postgres
--

CREATE SEQUENCE lthdtbtl.provinces_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE lthdtbtl.provinces_id_seq OWNER TO postgres;

--
-- TOC entry 4917 (class 0 OID 0)
-- Dependencies: 231
-- Name: provinces_id_seq; Type: SEQUENCE OWNED BY; Schema: lthdtbtl; Owner: postgres
--

ALTER SEQUENCE lthdtbtl.provinces_id_seq OWNED BY lthdtbtl.provinces.id;


--
-- TOC entry 233 (class 1259 OID 24917)
-- Name: student; Type: TABLE; Schema: lthdtbtl; Owner: postgres
--

CREATE TABLE lthdtbtl.student (
    id integer NOT NULL,
    name character varying NOT NULL,
    dob date NOT NULL,
    address_province integer,
    province integer
);


ALTER TABLE lthdtbtl.student OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 33129)
-- Name: student_class; Type: TABLE; Schema: lthdtbtl; Owner: postgres
--

CREATE TABLE lthdtbtl.student_class (
    student_id integer NOT NULL,
    class_id integer NOT NULL
);


ALTER TABLE lthdtbtl.student_class OWNER TO postgres;

--
-- TOC entry 4740 (class 2604 OID 41331)
-- Name: certificate id; Type: DEFAULT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.certificate ALTER COLUMN id SET DEFAULT nextval('lthdtbtl.certificate_id_seq'::regclass);


--
-- TOC entry 4739 (class 2604 OID 41305)
-- Name: class id; Type: DEFAULT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.class ALTER COLUMN id SET DEFAULT nextval('lthdtbtl.class_id_seq'::regclass);


--
-- TOC entry 4738 (class 2604 OID 24912)
-- Name: provinces id; Type: DEFAULT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.provinces ALTER COLUMN id SET DEFAULT nextval('lthdtbtl.provinces_id_seq'::regclass);


--
-- TOC entry 4907 (class 0 OID 41328)
-- Dependencies: 238
-- Data for Name: certificate; Type: TABLE DATA; Schema: lthdtbtl; Owner: postgres
--

COPY lthdtbtl.certificate (id, student_id, class_id, status) FROM stdin;
\.


--
-- TOC entry 4905 (class 0 OID 41302)
-- Dependencies: 236
-- Data for Name: class; Type: TABLE DATA; Schema: lthdtbtl; Owner: postgres
--

COPY lthdtbtl.class (id, name, start_date, end_date, description) FROM stdin;
1	LTW	2012-01-01	2014-02-02	aa
\.


--
-- TOC entry 4901 (class 0 OID 24909)
-- Dependencies: 232
-- Data for Name: provinces; Type: TABLE DATA; Schema: lthdtbtl; Owner: postgres
--

COPY lthdtbtl.provinces (id, name) FROM stdin;
1	An Giang
2	Bà Rịa - Vũng Tàu
3	Bắc Giang
4	Bắc Kạn
5	Bạc Liêu
6	Bắc Ninh
7	Bến Tre
8	Bình Định
9	Bình Dương
10	Bình Phước
11	Bình Thuận
12	Cà Mau
13	Cần Thơ
14	Cao Bằng
15	Đà Nẵng
16	Đắk Lắk
17	Đắk Nông
18	Điện Biên
19	Đồng Nai
20	Đồng Tháp
21	Gia Lai
22	Hà Giang
23	Hà Nam
24	Hà Nội
25	Hà Tĩnh
26	Hải Dương
27	Hải Phòng
28	Hậu Giang
29	Hòa Bình
30	Hưng Yên
31	Khánh Hòa
32	Kiên Giang
33	Kon Tum
34	Lai Châu
35	Lâm Đồng
36	Lạng Sơn
37	Lào Cai
38	Long An
39	Nam Định
40	Nghệ An
41	Ninh Bình
42	Ninh Thuận
43	Phú Thọ
44	Phú Yên
45	Quảng Bình
46	Quảng Nam
47	Quảng Ngãi
48	Quảng Ninh
49	Quảng Trị
50	Sóc Trăng
51	Sơn La
52	Tây Ninh
53	Thái Bình
54	Thái Nguyên
55	Thanh Hóa
56	Thừa Thiên Huế
57	Tiền Giang
58	TP Hồ Chí Minh
59	Trà Vinh
60	Tuyên Quang
61	Vĩnh Long
62	Vĩnh Phúc
63	Yên Bái
\.


--
-- TOC entry 4902 (class 0 OID 24917)
-- Dependencies: 233
-- Data for Name: student; Type: TABLE DATA; Schema: lthdtbtl; Owner: postgres
--

COPY lthdtbtl.student (id, name, dob, address_province, province) FROM stdin;
1	NNN	2023-01-01	1	1
\.


--
-- TOC entry 4903 (class 0 OID 33129)
-- Dependencies: 234
-- Data for Name: student_class; Type: TABLE DATA; Schema: lthdtbtl; Owner: postgres
--

COPY lthdtbtl.student_class (student_id, class_id) FROM stdin;
1	1
\.


--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 237
-- Name: certificate_id_seq; Type: SEQUENCE SET; Schema: lthdtbtl; Owner: postgres
--

SELECT pg_catalog.setval('lthdtbtl.certificate_id_seq', 1, false);


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 235
-- Name: class_id_seq; Type: SEQUENCE SET; Schema: lthdtbtl; Owner: postgres
--

SELECT pg_catalog.setval('lthdtbtl.class_id_seq', 1, true);


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 231
-- Name: provinces_id_seq; Type: SEQUENCE SET; Schema: lthdtbtl; Owner: postgres
--

SELECT pg_catalog.setval('lthdtbtl.provinces_id_seq', 63, true);


--
-- TOC entry 4750 (class 2606 OID 41333)
-- Name: certificate certificate_pkey; Type: CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.certificate
    ADD CONSTRAINT certificate_pkey PRIMARY KEY (id);


--
-- TOC entry 4748 (class 2606 OID 41309)
-- Name: class class_pkey; Type: CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.class
    ADD CONSTRAINT class_pkey PRIMARY KEY (id);


--
-- TOC entry 4742 (class 2606 OID 24916)
-- Name: provinces provinces_pk; Type: CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.provinces
    ADD CONSTRAINT provinces_pk PRIMARY KEY (id);


--
-- TOC entry 4746 (class 2606 OID 33133)
-- Name: student_class student_class_pkey; Type: CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.student_class
    ADD CONSTRAINT student_class_pkey PRIMARY KEY (student_id, class_id);


--
-- TOC entry 4744 (class 2606 OID 24923)
-- Name: student student_pk; Type: CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.student
    ADD CONSTRAINT student_pk PRIMARY KEY (id);


--
-- TOC entry 4755 (class 2606 OID 41339)
-- Name: certificate certificate_class_id_fkey; Type: FK CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.certificate
    ADD CONSTRAINT certificate_class_id_fkey FOREIGN KEY (class_id) REFERENCES lthdtbtl.class(id);


--
-- TOC entry 4756 (class 2606 OID 41334)
-- Name: certificate certificate_student_id_fkey; Type: FK CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.certificate
    ADD CONSTRAINT certificate_student_id_fkey FOREIGN KEY (student_id) REFERENCES lthdtbtl.student(id);


--
-- TOC entry 4753 (class 2606 OID 33139)
-- Name: student_class student_class_class_id_fkey; Type: FK CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.student_class
    ADD CONSTRAINT student_class_class_id_fkey FOREIGN KEY (class_id) REFERENCES public.class(id);


--
-- TOC entry 4754 (class 2606 OID 33134)
-- Name: student_class student_class_student_id_fkey; Type: FK CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.student_class
    ADD CONSTRAINT student_class_student_id_fkey FOREIGN KEY (student_id) REFERENCES lthdtbtl.student(id);


--
-- TOC entry 4751 (class 2606 OID 24924)
-- Name: student student_provinces_fk; Type: FK CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.student
    ADD CONSTRAINT student_provinces_fk FOREIGN KEY (address_province) REFERENCES lthdtbtl.provinces(id);


--
-- TOC entry 4752 (class 2606 OID 24929)
-- Name: student student_provinces_fk_1; Type: FK CONSTRAINT; Schema: lthdtbtl; Owner: postgres
--

ALTER TABLE ONLY lthdtbtl.student
    ADD CONSTRAINT student_provinces_fk_1 FOREIGN KEY (province) REFERENCES lthdtbtl.provinces(id);


-- Completed on 2024-11-16 21:54:52

--
-- PostgreSQL database dump complete
--

