<html>
<head>
<title>list student</title>
</head>
<body>
	<?php session_start(); ?>
	<table border="1">
		<tr>
			<th>avatar</th>
			<th>ma sinh vien</th>
			<th>ten sinh vien</th>
			<th>tuoi</th>
			<th>gioi tinh</th>
			<th>dia chi</th>
			<th>action</th>
		</tr>
		<?php if (isset($_SESSION["listStudent"])) :?>
			<?php foreach ($_SESSION["listStudent"] as $student) : ?>
				<tr>
					<td>
						<img alt="<?= $student['id'] ?>" src="<?= $student['avatar'] ?>" style="width: 30px; height: 30px" />
					</td>
					<td><?= $student["id"] ?></td>
					<td><?= $student["name"] ?></td>
					<td><?= $student["age"] ?></td>
					<td><?= $student["sex"] ?></td>
					<td><?= $student["address"] ?></td>
					<td><a href="editStudent.php?id=<?= $student['id'] ?>">Edit</a> <a
						href="deleteStudent.php?id=<?= $student['id'] ?>">Delete</a></td>
				</tr>
			<?php endforeach; ?>
		<?php else :?>
			<tr>
				<td colspan="7">Danh sach sinh vien trong</td>
			</tr>
		<?php endif; ?>
	</table>
	<a href="addStudent.php">Them sinh vien</a>
</body>
</html>