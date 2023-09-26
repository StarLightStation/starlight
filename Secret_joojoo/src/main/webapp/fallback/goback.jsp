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
            confirmButtonText: '클라이언트 요청 처리에 실패 했습니다.'
        }).then((result) => {
            if (result.isConfirmed) {
                window.history.go(-1);
            }
        });
    };
</script>

</body>
</html>