<html>
	<head>
	<title>gio hoa</title>
	</head>
	<body>
		<form action="muahoa.php" method="post">
			<table border="1">
				<tr>
					<th colspan="2">Mua hoa</th>
				</tr>
				<tr>
					<td>id hoa</td>
					<td><input type="text" name="idHoa" /></td>
				</tr>
				<tr>
					<td>ten hoa</td>
					<td><input type="text" name="tenHoa" /></td>
				</tr>
				<tr>
					<td>so luong</td>
					<td><input type="text" name="soLuong" /></td>
				</tr>
				<tr>
					<td>don gia</td>
					<td><input type="text" name="donGia" /></td>
				</tr>
			</table>
			<input type="submit" value="Mua hoa" />
		</form>
		<?php
		session_start ();
		if (isset ( $_POST ["idHoa"] ) && isset ( $_POST ["tenHoa"] ) && isset ( $_POST ["soLuong"] ) && isset ( $_POST ["donGia"] ) && $_POST ["idHoa"] != "" && $_POST ["tenHoa"] != "" && $_POST ["soLuong"] != "" && $_POST ["donGia"] != null) {
			$idHoa = $_POST ["idHoa"];
			if (isset ( $_SESSION [$idHoa] )) {
				$_SESSION [$idHoa] ["soLuong"] += $_POST ["soLuong"];
				$_SESSION [$idHoa] ["donGia"] = $_POST ["donGia"];
			} else {
				$_SESSION [$idHoa] = array (
						"tenHoa" => $_POST ["tenHoa"],
						"soLuong" => $_POST ["soLuong"],
						"donGia" => $_POST ["donGia"] 
				);
			}
			header ( "location:giohoa.php" );
		}
		?>
	</body>
</html>