create database corenuvudb;

CREATE TABLE public.client (
	id integer NOT NULL,
	name character varying(200) NOT NULL,
	document character varying(200) NOT NULL,
	phone character varying(50),
	email character varying(100),
	active boolean NOT NULL
);
ALTER TABLE ONLY public.client
    ADD CONSTRAINT paymentcard_pk PRIMARY KEY (id);
ALTER TABLE public.client OWNER TO postgres;


CREATE SEQUENCE public.client_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
	
ALTER TABLE public.client_id_seq OWNER TO postgres;	

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;
ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


CREATE TABLE public.paymentcard (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    idclient integer NOT NULL,
    number character varying(16) NOT NULL,
    crypto character varying(4),
    start character varying(5) NOT NULL,
    expiry character varying(5) NOT NULL,
    is_active boolean NOT NULL,  
    brand_code character varying
);
ALTER TABLE ONLY public.paymentcard
    ADD CONSTRAINT paymentcard_pk PRIMARY KEY (id);

ALTER TABLE public.paymentcard OWNER TO postgres;


CREATE SEQUENCE public.paymentcard_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
	
ALTER TABLE public.paymentcard_id_seq OWNER TO postgres;	

ALTER SEQUENCE public.paymentcard_id_seq OWNED BY public.paymentcard.id;
ALTER TABLE ONLY public.paymentcard ALTER COLUMN id SET DEFAULT nextval('public.paymentcard_id_seq'::regclass);


CREATE TABLE public.core_client (
    id integer NOT NULL,
    name character varying(200) NOT NULL,
    url character varying(300),
    key character varying(300) NOT NULL,
    active boolean NOT NULL,  
    mode character varying DEFAULT 'TEST'::character varying NOT NULL,    
);