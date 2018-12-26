<pre>
<?php
// print_r ( $_POST );
// print_r ( $_FILES );
// print_r ( $_SERVER );
if (isset($_POST['uploadFile'])) {
    if ($_FILES['file']['error'] == 0) {
        // get file name upload - noi them ngay gio de tranh trung lap
        $fileName = date("Y_m_d_h_i_sa") . $_FILES['file']['name'];

        // get file tmp upload
        $tmpFile = $_FILES['file']['tmp_name'];

        // get dir root
        $pathRoot = $_SERVER['DOCUMENT_ROOT'];

        // create dir htdoc/file_up_load if file_up_load is not exist
        if (! is_dir("$pathRoot/file_up_load/")) {
            mkdir("$pathRoot/file_up_load/");
        }

        // create path dir upload
        $pathUpload = "$pathRoot/file_up_load/$fileName";

        // copy file from tmp dir to file_up_load dir
        move_uploaded_file($tmpFile, $pathUpload);

        $pathImage = "/file_up_load/$fileName";
    }
}
?>
</pre>

<html>
<head></head>
<body>
	<?php if (isset ( $_POST ['uploadFile'] ) && $_FILES ['file'] ['error'] != 4) : ?>
		<img alt="avatar" src="<?=$pathImage?>" />
	<?php else :?>
		<h1>Upload loi</h1>
	<?php endif; ?>
</body>
</html>