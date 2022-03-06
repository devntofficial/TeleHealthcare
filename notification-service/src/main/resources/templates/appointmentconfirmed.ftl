<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p>Hi ${appointment.userName},</p>
<p>
    Your appointment is confirmed with Dr. ${appointment.doctorName}.
    Booking Date: ${appointment.appointmentDate?date}
    Time Slot: ${appointment.timeSlot}
</p>

<p>Regards,</p>
<p>
    <em>Book My Consultation Appointments Team</em> <br />
</p>
</body>
</html>