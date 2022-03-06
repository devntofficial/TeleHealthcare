<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p>Hi ${prescription.userName},</p>
<p>
    Here are the prescriptions for your recent visit: <br>
    Diagnosis: ${prescription.diagnosis}<br>
</p>
<h3>Prescriptions:</h3>
<table>
    <tr>
        <th>Medicine Name</th>
        <th>Type</th>
        <th>Dosage</th>
        <th>Duration</th>
        <th>Frequency</th>
        <th>Remarks</th>
    </tr>

    <#list prescription.medicineList as medicine>
    <tr>
        <td>${medicine.name}</td>
        <td>${medicine.type}</td>
        <td>${medicine.dosage}</td>
        <td>${medicine.duration}</td>
        <td>${medicine.frequency}</td>
        <td>${medicine.remarks}</td>
    </tr>
    </#list>

</table>

<p>Regards,</p>
<p>
    <em>Dr. ${prescription.doctorName}</em> <br />
</p>
</body>
</html>