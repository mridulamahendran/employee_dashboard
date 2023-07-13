
# Employee Dashboard

This project is an employee dashboard developed during my internship at Siemens. 

The dashboard provides a user-friendly interface for capturing and storing employee details, including name, gender, designation, years of experience, primary skills and secondary skills. When users submit the employee information, it is securely stored in a MySQL database using the robust Spring Boot framework in conjunction with Hibernate for efficient data management and persistence. The employee dashboard serves as a centralized system for effectively managing employee data within the organization.

The frontend of the employee dashboard is built using Angular, a flexible and powerful JavaScript framework. Angular seamlessly integrates with RESTful APIs, enabling efficient communication between the frontend and backend. 

### Technologies Used:
- **Backend:** Spring Boot, Hibernate, MySQL
- **Frontend:** Angular
- **Charting:** Chart.js (JavaScript library for data visualization)

### Functionality:
* **Employee Data Capture:** The employee dashboard allows users to enter essential details of employees, including their name, gender, designation, years of experience, primary skills and secondary skills. The intuitive user interface guides users through the process of entering accurate and comprehensive employee information. The entered employee details would be sent as requests from the frontend to the backend API endpoints. The backend would then handle the requests, extract the employee details from the requests, and store them in the database.
* **Data Storage:** The entered employee details are securely stored in the database, ensuring their preservation for future retrieval and analysis. The dashboard leverages robust backend technologies, such as the Spring Boot framework and Hibernate, to handle the storage and management of employee data in a reliable and efficient manner.
* **Department-specific Statistics:** The employee dashboard leverages REST API calls to fetch the necessary data from the backend for generating department-specific statistics. The landing page displays four department cards, each indicating the number of employees in that department. When a user clicks on a department card on the landing page, the frontend sends a REST API request to the backend, specifying the selected department. The backend, equipped with the RESTful API endpoints, handles the request and retrieves the relevant data from the database based on the selected department. This data includes the number of employees in specific designations, gender distribution, and employee categorization by years of experience. Once the backend processes the request and retrieves the data, it sends a response back to the frontend. The frontend, in turn, utilizes the charting capabilities of Chart.js to visualize the fetched statistics in the form of charts and graphs. These visual representations provide an intuitive and informative display of department-specific employee data, facilitating easy comprehension and analysis.
* **Employee List:** The stored employee information is seamlessly fetched from the backend and presented in the "Employee list" menu on the frontend. By utilizing REST API calls, the dashboard dynamically populates the menu with the latest employee data. This provides users with a comprehensive overview of all entered employee details in an organized and easily accessible format.
* **Sorting and Filtering:** The "Employee list" menu incorporates sorting and filtering functionalities to facilitate efficient data exploration. Users can filter employees based on departments, enabling a focused view of specific teams. Additionally, sorting options are available, allowing employees to be arranged alphabetically, by years of experience, or according to other relevant criteria. This flexibility empowers users to quickly access and analyze the desired employee data.
