<html>
	<head>
		<title>gio hoa</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>ten san pham</th>
				<th>gia</th>
				<th>so luong</th>
				<th>tong tien</th>
			</tr>
			<?php
			session_start ();
			$thanhTien = 0;
			?>
			<?php foreach ( $_SESSION as $itemHoa ) :?>
				<tr>
					<td><?=$itemHoa ["tenHoa"]?></td>
					<td><?=$itemHoa ["donGia"]?></td>
					<td><input value="<?=$itemHoa ["soLuong"]?>" /></td>
					<td><?=($itemHoa ["donGia"] * $itemHoa ["soLuong"])?></td>
				</tr>
				<?php $thanhTien += ($itemHoa ["donGia"] * $itemHoa ["soLuong"]); ?>
			<?php endforeach?>
			<tr>
				<td colspan="4">Thanh tien :<?=$thanhTien ?></td>
			</tr>
		</table>
	</body>
</html>