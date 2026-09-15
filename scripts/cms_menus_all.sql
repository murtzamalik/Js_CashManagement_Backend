-- CMS feature menus under Payments module (MODULE_ID=4 typical). Adjust IDs if needed.
-- Assign to BBO (1), ADMIN (124), FINANCE OFFICER (24) — same pattern as IBFT/Bill Pay.

-- Example inserts (run after verifying next MENU_ID and parent under Payments):
/*
INSERT INTO TBL_MENU (MENU_ID, MODULE_ID, MENU_NAME, MENU_URL, MENU_TYPE, PARENT_MENU_ID, SEQUENCE_NO, IS_ACTIVE, CREATEDATE, CREATEUSER)
VALUES (TBL_MENU_SEQ.NEXTVAL, 4, 'Payment Queue', '/paymentqueue', 'P', <PAYMENTS_PARENT>, 50, 'Y', SYSDATE, 1);

INSERT INTO TBL_MENU (...) VALUES (..., 'Reports', '/reports', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Transaction MIS', '/transactionmis', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Balance Inquiry', '/balanceinquiry', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Beneficiaries', '/beneficiaries', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'RTGS', '/rtgs', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Pay Order', '/payorder', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Salary Payment', '/salarypay', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Tax Payment', '/taxpay', ...);
INSERT INTO TBL_MENU (...) VALUES (..., 'Collections Journey', '/collectionsjourney', ...);

-- Then TBL_ROLE_RIGHT for role_id 1, 124, 24
*/

-- Until menus are seeded, all screens are reachable from Corporate Dashboard quick actions.
SELECT 'Use Dashboard quick actions if menus not seeded yet' AS NOTE FROM DUAL;
