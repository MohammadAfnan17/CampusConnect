<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>T and P Registration</title>
  <style>
    body {
      background: #f1f3f6;
      font-family: Arial, sans-serif;
      padding: 20px;
    }
    .container {
      max-width: 700px;
      margin: auto;
      background: #fff;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2, h3 {
      text-align: center;
      color: #003366;
      margin-bottom: 15px;
    }
    form {
      display: flex;
      flex-direction: column;
    }
    input, textarea {
      margin-bottom: 15px;
      padding: 10px;
      font-size: 1rem;
      border: 1px solid #ccc;
      border-radius: 6px;
    }
    textarea {
      resize: vertical;
      height: 80px;
    }
    input[type="submit"] {
      background-color: #003366;
      color: white;
      font-weight: bold;
      cursor: pointer;
      transition: background 0.3s ease;
    }
    input[type="submit"]:hover {
      background-color: #0055aa;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Training and Placement Registration Form</h2>

    <form action="TpRegister" method="post" id="tpForm" >
      
      <h3>Student Info</h3>
      <input type="text" name="name" placeholder="Full Name">
      <input type="text" name="rollno" placeholder="Roll Number" >
      <input type="text" name="gender" placeholder="Gender" >
      <input type="text" name="mobile" placeholder="Mobile Number" >
      <input type="date" name="dob" >
       <input type="text" name="aadhar" placeholder="Aadhar Number" >
      <input type="email" name="domain_email" placeholder="College Email" >
      <input type="email" name="personal_email" placeholder="Personal Email" >
      <textarea name="current_address" placeholder="Current Address" ></textarea>
      <textarea name="permanent_address" placeholder="Permanent Address" ></textarea> 
 
       
       
      <input type="text" name="sample_date" >
      
    <input type="text" name="payment_reference" placeholder="Transaction Ref. No" >
      <input type="number"  name="amount_paid" placeholder="Amount Paid" >

      <h3>Academic Info</h3>
      <input type="text" name="btech_branch" placeholder="B.Tech Branch" required>
      <input type="number" name="btech_cgpa" placeholder="B.Tech CGPA" required>
      <input type="number" name="btech_backlogs" placeholder="Number of Backlogs" required>
      <input type="number" name="btech_gaps" placeholder="Study Gaps (if any)" required>
      <input type="number" name="ssc_marks" placeholder="SSC % / CGPA" required>
      <input type="number" name="inter_marks" placeholder="Inter/Diploma %" required>
      <input type="text" name="passing_year" placeholder="Passing Year (e.g., 2027)" required pattern="\d{4}" />
      
      <!--  <h3>Upload Documents</h3>-->
      <!--  <label>Upload SSC Memo (PDF/Image):</label>
      <input type="file" name="ssc_memo" accept=".pdf,.jpg,.jpeg,.png" required>

      <label>Upload Inter/Diploma Memo (PDF/Image):</label>
      <input type="file" name="inter_memo" accept=".pdf,.jpg,.jpeg,.png" required>-->
      

      <h3>Placement Preferences</h3>
      <input type="text" name="option_selected" placeholder="Training/Placement/Both" required>
      <input type="text" name="first_priority" placeholder="1st Priority Domain" required>
      <input type="text" name="second_priority" placeholder="2nd Priority Domain" required>

      <h3>Parent Info</h3>
      <input type="text" name="father_name" placeholder="Father's Name" required>
      <input type="text" name="father_mobile" placeholder="Father's Mobile No." required>
      <input type="text" name="mother_name" placeholder="Mother's Name" required>
      <input type="text" name="mother_mobile" placeholder="Mother's Mobile No." required>

      <input type="submit" value="Submit">
    </form>
  </div>

   <script>
    document.getElementById("tpForm").addEventListener("submit", function(e) {
      e.preventDefault(); // prevent default submission

      const enteredOtp = prompt("Enter the OTP:");

      if (enteredOtp === "1234") {
        alert("OTP verified. Submitting form...");
        this.submit(); // allow form to submit
      } else {
        alert("Invalid OTP. Please try again.");
      }
    });
  </script> 
</body>
</html>
