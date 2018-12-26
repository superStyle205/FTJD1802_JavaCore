<html>
<head>
<title>add student</title>
</head>
<body>
	<form action="addStudent.php" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>ma sinh vien :</th>
				<td><input type="text" name="idStudent"></td>
			</tr>
			<tr>
				<th>ten sinh vien :</th>
				<td><input type="text" name="nameStudent"></td>
			</tr>
			<tr>
				<th>tuoi :</th>
				<td><input type="text" name="ageStudent"></td>
			</tr>
			<tr>
				<th>gioi tinh :</th>
				<td><input type="text" name="sexStudent"></td>
			</tr>
			<tr>
				<th>dia chi :</th>
				<td><input type="text" name="addressStudent"></td>
			</tr>
			<tr>
				<th>avatar :</th>
				<td><input type="file" name="avatarStudent"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add"></td>
			</tr>
		</table>
	</form>
	<?php
    session_start();
    if (isset($_POST["idStudent"]) && isset($_POST["nameStudent"]) && isset($_POST["ageStudent"]) && isset($_POST["sexStudent"]) && isset($_POST["addressStudent"])) {
        if ($_POST["idStudent"] != "" && $_POST["nameStudent"] != "" && $_POST["ageStudent"] != "" && $_POST["sexStudent"] != "" && $_POST["addressStudent"] != "") {
            $idStudent = $_POST['idStudent'];
            $fileName = date("Y_m_d_h_i_sa") . $_FILES['avatarStudent']['name'];
    
            // get file tmp upload
            $tmpFile = $_FILES['avatarStudent']['tmp_name'];
    
            // get dir root
            $pathRoot = $_SERVER['DOCUMENT_ROOT'];
    
            // create dir htdoc/file_up_load if file_up_load is not exist
            if (! is_dir("$pathRoot/file_up_load")) {
                mkdir("$pathRoot/file_up_load");
            }
    
            mkdir("$pathRoot/file_up_load/$idStudent");
    
            // create path dir upload
            $pathUpload = "$pathRoot/file_up_load/$idStudent/$fileName";
    
            // copy file from tmp dir to file_up_load dir
            move_uploaded_file($tmpFile, $pathUpload);
    
            $student = array(
                "id" => $_POST["idStudent"],
                "name" => $_POST["nameStudent"],
                "age" => $_POST["ageStudent"],
                "sex" => $_POST["sexStudent"],
                "address" => $_POST["addressStudent"],
                "avatar" => "/file_up_load/$idStudent/$fileName"
            );
    
            $_SESSION["listStudent"][$_POST["idStudent"]] = $student;
    
            header("location:listStudent.php");
        }
    }
	?>
</body>
</html>