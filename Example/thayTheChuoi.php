<html>
<head>
<title>bai ba</title>
</head>
<body>
	<form action="thayTheChuoi.php" method="post">
		Doan van ban goc :<br /> <input name="textOriginal" /><br />
		Tu goc : <input name="textNeedReplace" /> thay the bang <input name="textRepalce" /><br />
		<input type="submit" />
	</form>
	<?php
    if (isset($_POST["textOriginal"])) {
        $output = str_replace($_POST["textNeedReplace"], $_POST["textRepalce"], $_POST["textOriginal"]);
        echo "Output <input type='text' value='$output'/>";
    } else {
        echo "Output <input type='text'/>";
    }
    ?>
</body>
</html>