<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

</head>

<body>

<c:if test="${mdata.sweetAlert eq 'success'}">
    <script>
        swal('성공 !', '${mdata.message} 성공 했습니다.', 'success').then(function () {
            window.location.href = "${mdata.location}";
        });
    </script>
</c:if>

<c:if test="${mdata.sweetAlert eq 'fail'}">
    <script>
    swal({
        title: '실패 !',
        text: `${mdata.message} 실패 했습니다.`,
        icon: 'warning',
        content: {
            element: "div",
            attributes: {
                innerHTML: "다시 확인해주세요.",
                style: "text-align: center;"
            }
        },
        buttons: {
            confirm: {
                text: '확인',
                className: 'btn-custom'
            }
        }
    }).then(function () {
        window.location.href = "${mdata.location}";
    });
</script>
</c:if>

</body>

</html>