<html>
<head>
<title>edit student</title>
</head>
<body>
	<?php session_start (); ?>
	<?php if (isset ( $_GET ['id'] )) : ?>
		<?php $student = $_SESSION ['listStudent'] [$_GET ['id']]; ?>
		<form action="editStudent.php" method="post" enctype="multipart/form-data">
			<table border="1">
				<tr>
					<th>ma sinh vien :</th>
					<td>
						<input type="hidden" name="idStudent" value="<?= $student['id'] ?>" />
						<?= $student["id"]?>
					</td>
				</tr>
				<tr>
					<th>ten sinh vien :</th>
					<td>
						<input type="text" name="nameStudent" value="<?= $student['name'] ?>" />
					</td>
				</tr>
				<tr>
					<th>tuoi :</th>
					<td>
						<input type="text" name="ageStudent" value="<?= $student['age'] ?>" />
					</td>
				</tr>
				<tr>
					<th>gioi tinh :</th>
					<td>
						<input type="text" name="sexStudent" value="<?= $student['sex'] ?>" />
					</td>
				</tr>
				<tr>
					<th>dia chi :</th>
					<td>
						<input type="text" name="addressStudent" value="<?= $student['address'] ?>" />
					</td>
				</tr>
				<tr>
					<th>avatar :</th>
					<td>
						<input type="hidden" name="avatarHidden" value="<?= $student['avatar'] ?>" />
						<input type="file" name="avatarStudent" />
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Edit"></td>
				</tr>
			</table>
		</form>
	<?php endif; ?>
	<?php
	if (isset($_POST["idStudent"]) && isset($_POST["nameStudent"]) && isset($_POST["ageStudent"]) && isset($_POST["sexStudent"]) && isset($_POST["addressStudent"])) {
		if ($_POST["idStudent"] != "" && $_POST["nameStudent"] != "" && $_POST["ageStudent"] != "" && $_POST["sexStudent"] != "" && $_POST["addressStudent"] != "") {
			if (isset($_FILES["avatarStudent"]) && $_FILES["avatarStudent"]["error"] == 0) {
				$idStudent = $_POST['idStudent'];
				$fileName = date("Y_m_d_h_i_sa") . $_FILES["avatarStudent"]["name"];
	
				// get file tmp upload
				$tmpFile = $_FILES['avatarStudent']['tmp_name'];
	
				// get dir root
				$pathRoot = $_SERVER['DOCUMENT_ROOT'];
	
				// create path dir upload
				$pathUpload = "$pathRoot/file_up_load/$idStudent/$fileName";
	
				// copy file from tmp dir to file_up_load dir
				move_uploaded_file($tmpFile, $pathUpload);
	
				$avatar = "/file_up_load/$idStudent/$fileName";
			} else {
				$avatar = $_POST["avatarHidden"];
			}
			$student = array(
				"id" => $_POST["idStudent"],
				"name" => $_POST["nameStudent"],
				"age" => $_POST["ageStudent"],
				"sex" => $_POST["sexStudent"],
				"address" => $_POST["addressStudent"],
				"avatar" => $avatar
			);
	
			$_SESSION["listStudent"][$_POST["idStudent"]] = $student;
	
			header("location:listStudent.php");
		}
	}
	?>
</body>
</html>