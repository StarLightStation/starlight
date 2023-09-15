<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.css">
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.5/dist/sweetalert2.min.js"></script>
<script>
    window.onload = function () {
        swal.fire({
            title: '${title}',
            text: '${text}',
            icon: '${icon}',
            confirmButtonText: '알 수 없는 요청 입니다. 이전 페이지로 돌아가기.'
        }).then((result) => {
            if (result.isConfirmed) {
                window.history.go(-1);
            }
        });
    };
</script>

</body>
</html>