INSERT INTO
	ORD_PRODUCTOS
VALUES (?, ? , ?);


SELECT * FROM ORD_PRODUCTOS op;


SELECT 
	SUM(opr.precio)
FROM ORD_PEDIDOS op
LEFT JOIN ORD_DETALLE_PEDIDO odp ON op.codigo = odp.pedido_fk 
LEFT JOIN ORD_PRODUCTOS opr ON odp.producto_fk = opr.codigo 
WHERE op.codigo = 1;


SELECT 
	opr.nombre
FROM ORD_PEDIDOS op
LEFT JOIN ORD_DETALLE_PEDIDO odp ON op.codigo = odp.pedido_fk 
LEFT JOIN ORD_PRODUCTOS opr ON odp.producto_fk = opr.codigo 
WHERE op.codigo = 1;