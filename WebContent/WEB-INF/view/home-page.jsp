<!DOCTYPE html>
<html>
<head>

<title>CreditCard-HomePage</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">

</head>

<body id="a">

   <h1>Check for Credit Card Eligibility</h1>
 <div>     
<form action="checkEligibility" method="GET">
   <p id="panNo">Enter PAN Number: <input  type="text" name="PAN" required autofocus pattern="[a-zA-Z0-9]{0,10}" title="10 character Alphanumeric" /></p>
   <p id="submit"><input type="submit" value="check" ></p>
</form>
</div>     
 </body>
</html>