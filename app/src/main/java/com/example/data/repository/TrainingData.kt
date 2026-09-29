package com.example.data.repository

import com.example.R
import com.example.data.model.Course
import com.example.data.model.CourseCategory
import com.example.data.model.MockQuestion
import com.example.data.model.MockQuiz

object TrainingData {
    val courses = listOf(
        Course(
            id = "sia-door-supervisor",
            title = "SIA Door Supervisor Course (Level 2)",
            subtitle = "Most popular security qualification in London & nationwide UK",
            category = CourseCategory.SECURITY,
            price = "£199.99",
            duration = "6 Days",
            accreditation = "SIA Approved / Highfield Qualifications",
            passRate = "98.7%",
            nextDates = listOf("Monday, next week", "Thursday, next week", "Every Monday"),
            deliveryMode = "Classroom at London Romford Road Campus",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "The SIA Door Supervisor qualification enables you to work as both a Door Supervisor and a Static Security Officer in licensed premises, nightclubs, corporate headquarters, music festivals, retail centers, and public events across the UK. Fully compliant with modern SIA physical intervention and ACT counter-terror guidelines.",
            syllabus = listOf(
                "Unit 1: Principles of Working in the Private Security Industry",
                "Unit 2: Principles of Working as a Door Supervisor in the Private Security Industry",
                "Unit 3: Application of Conflict Management in the Private Security Industry",
                "Unit 4: Application of Physical Intervention Skills in the Private Security Industry"
            ),
            requirements = listOf(
                "Minimum age of 18 years",
                "Proof of Right to Work in the UK",
                "Two forms of photographic & address ID",
                "Emergency First Aid at Work (EFAW) certificate (offered on campus)"
            ),
            careerRoles = listOf(
                "Venue Door Supervisor (£14 - £18/hr)",
                "Corporate Security Officer (£13 - £16/hr)",
                "Festival & Arena Event Steward",
                "Retail Loss Prevention Specialist"
            ),
            imageResId = R.drawable.img_sia_security
        ),
        Course(
            id = "sia-cctv-operator",
            title = "SIA CCTV Operator Course (Public Space Surveillance)",
            subtitle = "Work in high-tech monitoring centers, councils & retail hubs",
            category = CourseCategory.SECURITY,
            price = "£175.00",
            duration = "3 Days",
            accreditation = "SIA Approved / Highfield Regulated",
            passRate = "99.1%",
            nextDates = listOf("Upcoming Wednesday", "Every alternate Wednesday"),
            deliveryMode = "Classroom & Practical Simulation Lab",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Gain the legal qualification needed to operate closed-circuit television cameras in public and private control rooms. Covers GDPR, Data Protection Act, camera maneuvering, evidentiary footage seizure, and police liaison protocols.",
            syllabus = listOf(
                "Unit 1: Principles of Working in the Private Security Industry",
                "Unit 2: Principles and Practices of Working as a CCTV Operator",
                "Unit 3: Practical Camera Control & Evidentiary Log Keeping",
                "Unit 4: Legal Framework, Surveillance Codes & Data Protection Act"
            ),
            requirements = listOf(
                "Age 18+",
                "Valid Government ID",
                "Basic English proficiency (Level 1 / B1 equivalent)"
            ),
            careerRoles = listOf(
                "Council Surveillance Officer (£15 - £20/hr)",
                "Retail Mall Control Room Specialist",
                "Airport / Rail CCTV Monitor"
            ),
            imageResId = R.drawable.img_sia_security
        ),
        Course(
            id = "sia-top-up",
            title = "SIA Door Supervisor Top-Up Refresher",
            subtitle = "Mandatory refresher for renewing your active SIA badge",
            category = CourseCategory.SECURITY,
            price = "£110.00",
            duration = "2 Days",
            accreditation = "Mandatory SIA Renewal Standard",
            passRate = "99.5%",
            nextDates = listOf("Every Tuesday & Saturday"),
            deliveryMode = "Classroom & Blended e-Learning",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Required by the Security Industry Authority (SIA) for existing Door Supervisors renewing their licence. Covers updated Physical Intervention tactics, Terror Threat Awareness (ACT Awareness & ACT Security), and critical incident management.",
            syllabus = listOf(
                "Unit 1: Terror Threat Awareness (ACT Security Certification)",
                "Unit 2: Managing Vulnerable Individuals and Spiking Incidents",
                "Unit 3: Updated Physical Intervention Techniques and Restraint Guidelines"
            ),
            requirements = listOf(
                "Existing SIA Door Supervisor or Security Officer badge",
                "Valid First Aid (EFAW) certificate"
            ),
            careerRoles = listOf(
                "Maintain active SIA Door Supervisor licence",
                "Continue uninterrupted security employment"
            ),
            imageResId = R.drawable.img_sia_security
        ),
        Course(
            id = "cscs-green-card",
            title = "CSCS Green Card Level 1 Health & Safety",
            subtitle = "Essential qualification to enter UK construction sites",
            category = CourseCategory.CONSTRUCTION,
            price = "£130.00",
            duration = "1 Day (Exam Included)",
            accreditation = "CITB & Highfield / Qualsafe Approved",
            passRate = "98.4%",
            nextDates = listOf("Daily Monday to Saturday"),
            deliveryMode = "Classroom or Online Live + Test Center",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Everything you need to obtain your official 5-year CSCS Green Card (Labourer Card). Includes the Level 1 Award in Health and Safety in a Construction Environment and the CITB Health, Safety and Environment touch-screen test.",
            syllabus = listOf(
                "Unit 1: Prevention of Accidents and Reporting Protocols (RIDDOR)",
                "Unit 2: Working at Height and Scaffolding Safety Rules",
                "Unit 3: Manual Handling, PPE and Machinery Hazards",
                "Unit 4: Hazardous Substances (COSHH) and Fire Safety on Site"
            ),
            requirements = listOf(
                "Age 16+",
                "No prior construction experience required",
                "UK Photographic ID"
            ),
            careerRoles = listOf(
                "Site Labourer (£13 - £17/hr)",
                "Trades Apprentice / Trades Assistant",
                "Construction Logistics Assistant"
            ),
            imageResId = R.drawable.img_cscs
        ),
        Course(
            id = "tfl-seru-course",
            title = "TFL SERU Test Preparation & Training",
            subtitle = "Safety, Equality & Regulatory Understanding for PCO drivers",
            category = CourseCategory.TAXI_TFL,
            price = "£150.00",
            duration = "2 Days Intensive + Online Hub",
            accreditation = "TFL Standards Aligned",
            passRate = "96.5%",
            nextDates = listOf("Every Monday, Wednesday & Saturday"),
            deliveryMode = "Classroom Computer Lab & Mock Tests",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Comprehensive masterclass for Transport for London's mandatory SERU exam. Designed to help prospective and existing Private Hire drivers pass the TFL assessment on the first attempt with extensive mock question banks and sentence completion drills.",
            syllabus = listOf(
                "TFL Driver Licensing Requirements & Obligations",
                "Safeguarding Children & Vulnerable Passengers",
                "Equality Act 2010 & Service Animals Guidance",
                "Emergency Protocols, Road Safety & Vehicle Inspection",
                "SERU Reading Comprehension & Drag-and-Drop Sentence Completion"
            ),
            requirements = listOf(
                "Valid UK Driving Licence (minimum 3 years)",
                "Applying for or renewing TFL PHV licence"
            ),
            careerRoles = listOf(
                "Licensed London Private Hire Driver (Uber, Bolt, Blacklane)",
                "Executive Chauffeur Driver"
            ),
            imageResId = R.drawable.img_taxi
        ),
        Course(
            id = "tfl-topographical-skills",
            title = "TFL Topographical Skills Training & Mock Assessment",
            subtitle = "Master London map reading & direct route planning",
            category = CourseCategory.TAXI_TFL,
            price = "£120.00",
            duration = "1 Day (4 Hours Intensive)",
            accreditation = "Official TFL Route Standard",
            passRate = "97.2%",
            nextDates = listOf("Tuesdays, Thursdays & Saturdays"),
            deliveryMode = "Dedicated Romford Road Mapping Lab",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Pass your official TFL Topographical test with 1-on-1 guidance from master instructors. Practice planning the most direct routes using the Master Atlas of Greater London, identifying road directions, roundabouts, one-way systems, and overcoming tricky Thames river crossings.",
            syllabus = listOf(
                "Understanding the Master Atlas of Greater London (Index & Grid references)",
                "Plotting Direct Routes between two locations",
                "Handling One-way streets, No-turn junctions and Restricted bridges",
                "Measuring shortest feasible mileage and major motorway junctions"
            ),
            requirements = listOf(
                "Applying for TFL Private Hire Driver badge"
            ),
            careerRoles = listOf(
                "London PHV / Minicab Operator Driver",
                "VIP Airport Transfer Chauffeur"
            ),
            imageResId = R.drawable.img_taxi
        ),
        Course(
            id = "teacher-training-aet",
            title = "Level 3 Award in Education and Training (AET / PTLLS)",
            subtitle = "Become an accredited trainer & instructor in your field",
            category = CourseCategory.TRAINER,
            price = "£249.00",
            duration = "3-4 Days / Fast-track Blended",
            accreditation = "Ofqual Regulated / Highfield & NCFE",
            passRate = "99.2%",
            nextDates = listOf("Every alternate Monday", "Weekend Cohorts Available"),
            deliveryMode = "Classroom or Online Live Micro-teaching",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "The premier introductory teaching qualification in the UK (formerly known as PTLLS). Essential for teaching vocational adults in security, construction, health & safety, first aid, corporate development, or colleges.",
            syllabus = listOf(
                "Understanding Roles, Responsibilities and Relationships in Education",
                "Understanding and Using Inclusive Teaching and Learning Approaches",
                "Understanding Assessment in Education and Training",
                "Delivering an Assessed 15-Minute Micro-Teaching Session"
            ),
            requirements = listOf(
                "Age 19+",
                "Competence in the vocational subject you wish to teach",
                "Good command of English (Level 2 equivalent)"
            ),
            careerRoles = listOf(
                "Vocational College Lecturer (£28k - £38k/yr)",
                "Corporate Trainer / Safety Instructor",
                "SIA Security Trainer",
                "First Aid & Health Safety Instructor"
            ),
            imageResId = R.drawable.img_esol
        ),
        Course(
            id = "assessor-taqa-cava",
            title = "Level 3 Certificate in Assessing Vocational Achievement (CAVA)",
            subtitle = "Qualify as a certified workplace & classroom assessor",
            category = CourseCategory.TRAINER,
            price = "£399.00",
            duration = "Self-Paced / 4-8 Weeks Mentored",
            accreditation = "NCFE / Highfield Qualifications",
            passRate = "98.8%",
            nextDates = listOf("Start Any Day (Rolling Admission)"),
            deliveryMode = "Blended Classroom + Practical Portfolio",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "The complete industry qualification for NVQ and Apprenticeship Assessors (formerly A1 / D32 / D33). Enables you to assess occupational competence in both work environments and training classrooms across England and the UK.",
            syllabus = listOf(
                "Unit 1: Understanding the Principles and Practices of Assessment",
                "Unit 2: Assess Occupational Competence in the Work Environment",
                "Unit 3: Assess Vocational Skills, Knowledge and Understanding"
            ),
            requirements = listOf(
                "Significant occupational competence in your vocational sector",
                "Access to learners to carry out practical assessments"
            ),
            careerRoles = listOf(
                "Apprenticeship Assessor (£30k - £42k/yr)",
                "Internal Quality Assurer (IQA) Pathway",
                "Vocational Examiner & Skills Auditor"
            ),
            imageResId = R.drawable.img_esol
        ),
        Course(
            id = "esol-a1-b1",
            title = "ESOL International (Levels A1 to B1)",
            subtitle = "UKVI Approved English for Visa, Citizenship & Licences",
            category = CourseCategory.ESOL,
            price = "£180.00",
            duration = "2-4 Weeks (Flexible morning / evening)",
            accreditation = "Ofqual Regulated / UKVI Recognized",
            passRate = "99.0%",
            nextDates = listOf("New cohorts every Monday"),
            deliveryMode = "Classroom or Interactive Online Live",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Accredited English for Speakers of Other Languages courses tailored for UK Visa applications (Spouse, Indefinite Leave to Remain, British Citizenship), workplace readiness, and professional taxi/security licences.",
            syllabus = listOf(
                "Conversational English in Everyday Situations",
                "Listening Comprehension and Workplace Dialogues",
                "Reading Official Notices, Signs, and Forms",
                "Grammar, Pronunciation & Vocabulary Development"
            ),
            requirements = listOf(
                "Open to all learners; initial level assessment provided on arrival"
            ),
            careerRoles = listOf(
                "Meets Home Office English Language Requirements",
                "Unlocks security, retail, and public service careers"
            ),
            imageResId = R.drawable.img_esol
        ),
        Course(
            id = "life-in-the-uk-prep",
            title = "Life in the UK Test Preparation",
            subtitle = "Pass your British Citizenship & Settlement test",
            category = CourseCategory.ESOL,
            price = "£140.00",
            duration = "1-2 Weeks Intensive",
            accreditation = "Official UK Government Syllabus",
            passRate = "98.5%",
            nextDates = listOf("Every Wednesday & Saturday"),
            deliveryMode = "Classroom Coaching & Mock Test Software",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Detailed preparation for the official 24-question Life in the UK test. Covers British values, history from prehistoric times to modern monarchy, government structure, legal system, traditions, sports, and key historical figures.",
            syllabus = listOf(
                "The Values and Principles of the UK",
                "What is the UK: Countries, Capitals & Geography",
                "A Long and Illustrious History: Monarchy, Empire & World Wars",
                "A Modern, Thriving Society: Culture, Religion & Festivals",
                "The UK Government, the Law and Your Role as a Citizen"
            ),
            requirements = listOf(
                "Preparing for Settlement / Naturalisation"
            ),
            careerRoles = listOf(
                "Pass certificate required for British Citizenship application"
            ),
            imageResId = R.drawable.img_life_uk
        ),
        Course(
            id = "health-social-care-level2",
            title = "Level 2 & 3 Health and Social Care & Infection Control",
            subtitle = "Care home, hospital & domiciliary support qualification",
            category = CourseCategory.HEALTH_CARE,
            price = "£210.00",
            duration = "2 Weeks / Flexible",
            accreditation = "NCFE / Skills for Care Aligned",
            passRate = "99.4%",
            nextDates = listOf("Every Monday"),
            deliveryMode = "Classroom & Practical Clinical Skills",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Gain essential skills for working in UK hospitals, care homes, and community healthcare. Covers safeguarding adults, infection control (COVID/Flu standards), medication handling, dementia care, and person-centered planning.",
            syllabus = listOf(
                "Principles of Safeguarding and Duty of Care",
                "Infection Prevention and Control in Care Settings",
                "Communication & Person-Centered Care",
                "Health, Safety and Moving & Handling in Healthcare"
            ),
            requirements = listOf(
                "Age 18+",
                "DBS check guidance provided"
            ),
            careerRoles = listOf(
                "Healthcare Assistant (NHS & Private, £13 - £17/hr)",
                "Senior Care Worker",
                "Support Worker"
            ),
            imageResId = R.drawable.img_esol
        ),
        Course(
            id = "it-first-line-support",
            title = "IT Technical Support (1st & 2nd Line) & Cyber Basics",
            subtitle = "Launch your corporate IT support & helpdesk career",
            category = CourseCategory.IT_TECH,
            price = "£299.00",
            duration = "3 Weeks / Weekend Sessions",
            accreditation = "CompTIA Aligned / Industry Standard",
            passRate = "97.8%",
            nextDates = listOf("Every Saturday Cohort"),
            deliveryMode = "Computer Lab & Network Workbench",
            campus = "252-256 Romford Road, London E7 9HZ",
            description = "Practical hands-on IT support training covering hardware diagnostics, Windows/Linux OS troubleshooting, Active Directory, Office 365 administration, networking fundamentals (TCP/IP, DNS, DHCP), and foundational cyber security defense.",
            syllabus = listOf(
                "PC Hardware Assembly, BIOS & Troubleshooting",
                "Windows 11/10 and Server Active Directory Administration",
                "Network Configuration: Routers, Switches, Subnetting & WiFi",
                "Cloud Support (Microsoft 365 / Azure Fundamentals)",
                "Cyber Security Basics & Phishing Prevention"
            ),
            requirements = listOf(
                "Passion for technology and basic computer literacy"
            ),
            careerRoles = listOf(
                "IT Helpdesk Support Engineer (£25k - £32k/yr)",
                "Desktop Support Specialist",
                "Junior Systems Administrator"
            ),
            imageResId = R.drawable.img_sia_security
        )
    )

    val mockQuizzes = listOf(
        MockQuiz(
            id = "mock-sia-door",
            title = "SIA Door Supervisor Practice Test",
            category = CourseCategory.SECURITY,
            description = "10 essential questions covering conflict management, law, search powers, and physical intervention.",
            passingScorePercent = 80,
            questions = listOf(
                MockQuestion(
                    id = 1,
                    question = "Under UK law, what is the legal basis for a Door Supervisor conducting a bag search at a licensed venue?",
                    options = listOf(
                        "Door Supervisors have statutory police search powers under PACE 1984",
                        "Searches are carried out with the customer's consent as a condition of entry",
                        "Door Supervisors can search anyone without consent inside the venue",
                        "Only local council officers can authorize a bag search"
                    ),
                    correctIndex = 1,
                    explanation = "Door Supervisors do not hold statutory police powers. Searches at licensed venues must be conducted with the voluntary consent of the individual as a condition of entry."
                ),
                MockQuestion(
                    id = 2,
                    question = "What must a licensed Door Supervisor do with their SIA licence card while on duty?",
                    options = listOf(
                        "Keep it stored safely in a locker or vehicle",
                        "Display it visibly on their outer clothing at all times unless an overt safety exemption applies",
                        "Show it only if an inspector specifically requests it",
                        "Leave it with the venue manager"
                    ),
                    correctIndex = 1,
                    explanation = "SIA regulations mandate that all active licence holders must display their licence on their outer clothing so it is clearly visible to the public and authorities."
                ),
                MockQuestion(
                    id = 3,
                    question = "When dealing with a potentially aggressive customer, what is the primary objective of Conflict Management?",
                    options = listOf(
                        "Physically restrain the person as quickly as possible",
                        "De-escalate the situation and reduce the risk of harm to all parties",
                        "Challenge the person verbally to show authority",
                        "Demand an immediate on-the-spot fine"
                    ),
                    correctIndex = 1,
                    explanation = "The main goal of conflict management is early de-escalation, using calm communication and non-threatening body language to resolve tension without violence."
                ),
                MockQuestion(
                    id = 4,
                    question = "Which piece of legislation governs the processing and recording of incident logs containing customer personal data?",
                    options = listOf(
                        "The Data Protection Act 2018 / UK GDPR",
                        "The Licensing Act 2003",
                        "The Private Security Industry Act 2001",
                        "The Health and Safety at Work Act 1974"
                    ),
                    correctIndex = 0,
                    explanation = "Any recording of personal names, photographs, or CCTV footage is subject to the UK GDPR and the Data Protection Act 2018."
                ),
                MockQuestion(
                    id = 5,
                    question = "What is the phenomenon known as 'Positional Asphyxia'?",
                    options = listOf(
                        "A temporary panic attack caused by strobe lighting",
                        "A dangerous reduction in oxygen intake caused when a person's body position restricts their breathing during restraint",
                        "A severe allergic reaction to cleaning chemicals",
                        "Dehydration caused by prolonged standing at venue doors"
                    ),
                    correctIndex = 1,
                    explanation = "Positional asphyxia occurs when a person is restrained in a position (especially prone face-down) that restricts their airway or chest movement, which can be fatal."
                ),
                MockQuestion(
                    id = 6,
                    question = "Under the Licensing Act 2003, which of the following is NOT one of the four statutory licensing objectives?",
                    options = listOf(
                        "The prevention of crime and disorder",
                        "Public safety",
                        "The prevention of public nuisance",
                        "Maximizing local business revenue and profit"
                    ),
                    correctIndex = 3,
                    explanation = "The 4 statutory objectives are: Prevention of Crime & Disorder, Public Safety, Prevention of Public Nuisance, and Protection of Children from Harm. Profit maximization is not an objective."
                ),
                MockQuestion(
                    id = 7,
                    question = "If a customer refuses to leave a licensed premise after being asked by management, what offence may they be committing?",
                    options = listOf(
                        "Trespass / failing to leave licensed premises under the Licensing Act",
                        "Criminal damage",
                        "Perjury",
                        "Arson"
                    ),
                    correctIndex = 0,
                    explanation = "Under section 143 of the Licensing Act 2003, a person who fails to leave licensed premises without reasonable excuse when requested commits an offence."
                ),
                MockQuestion(
                    id = 8,
                    question = "What is the recommended reaction distance (reaction gap) when communicating with a potentially volatile individual?",
                    options = listOf(
                        "Less than 30 centimeters",
                        "At least 1 to 2 meters (arm's length plus a step)",
                        "5 meters minimum at all times",
                        "Close physical contact"
                    ),
                    correctIndex = 1,
                    explanation = "Maintaining a safety gap of 1.5 to 2 meters gives you reaction time in case of a sudden strike and avoids crowding the individual's personal space."
                ),
                MockQuestion(
                    id = 9,
                    question = "Under what condition is physical intervention legally justified under Section 3 of the Criminal Law Act 1967?",
                    options = listOf(
                        "Whenever a customer insults a member of staff",
                        "When using reasonable and proportionate force in the prevention of crime or lawful arrest",
                        "Only after obtaining written permission from police",
                        "Whenever an event runs past midnight"
                    ),
                    correctIndex = 1,
                    explanation = "Section 3 of the Criminal Law Act 1967 permits the use of reasonable force as is reasonable in the circumstances in the prevention of crime."
                ),
                MockQuestion(
                    id = 10,
                    question = "What does the 'ACT' initiative in UK security training stand for?",
                    options = listOf(
                        "Action Against Conflict & Terrorism",
                        "Action Counters Terrorism",
                        "Accredited Control Tactics",
                        "Active Communication Technique"
                    ),
                    correctIndex = 1,
                    explanation = "ACT stands for Action Counters Terrorism, the official UK police counter-terrorism awareness training program now mandatory for SIA renewals."
                )
            )
        ),
        MockQuiz(
            id = "mock-tfl-seru",
            title = "TFL SERU Practice Test (PCO Taxi)",
            category = CourseCategory.TAXI_TFL,
            description = "10 questions based on the official TFL PHV Driver's Handbook on safety, equality, and regulations.",
            passingScorePercent = 80,
            questions = listOf(
                MockQuestion(
                    id = 1,
                    question = "Can a licensed London Private Hire Driver accept a passenger who hails them directly on the street without a prior operator booking?",
                    options = listOf(
                        "Yes, but only outside of central London",
                        "Yes, if the customer pays in cash upfront",
                        "No, accepting an unbooked street hail is illegal plying for hire and invalidates insurance",
                        "Yes, if it is raining heavily"
                    ),
                    correctIndex = 2,
                    explanation = "Only London licensed hackney carriages (Black Cabs) can be hailed on the street. All PHV journeys must be pre-booked through a licensed private hire operator."
                ),
                MockQuestion(
                    id = 2,
                    question = "Under the Equality Act 2010, what are a PHV driver's legal obligations regarding a passenger accompanied by an Assistance Dog?",
                    options = listOf(
                        "The driver can charge an additional £10 pet cleaning fee",
                        "The driver must carry the passenger and guide dog at no extra charge unless they hold an official medical exemption certificate",
                        "The driver can refuse the dog if the car has leather seats",
                        "The dog must be transported inside the boot of the car"
                    ),
                    correctIndex = 1,
                    explanation = "It is a criminal offence under the Equality Act 2010 to refuse or charge extra for an assistance dog unless the driver has been granted a medical exemption certificate by TfL."
                ),
                MockQuestion(
                    id = 3,
                    question = "Where must the official TFL PHV licence discs be displayed on the vehicle?",
                    options = listOf(
                        "Inside the glove compartment",
                        "On the front windscreen (top left/passenger side) and the rear window (bottom left) as specified by TfL",
                        "On the driver's side front door",
                        "Anywhere inside the vehicle as long as visible"
                    ),
                    correctIndex = 1,
                    explanation = "TfL regulations require PHV licence discs to be permanently affixed to the designated positions on the front and rear windscreens."
                ),
                MockQuestion(
                    id = 4,
                    question = "What should a private hire driver do if they suspect a passenger they are transporting may be a victim of modern slavery or child sexual exploitation?",
                    options = listOf(
                        "Confront the accompanying adult directly in the vehicle",
                        "Ignore it as it is none of the driver's business",
                        "Report the concerns immediately to police (999 if emergency, 101 or Crimestoppers), noting dates, times and descriptions",
                        "Post the details on social media"
                    ),
                    correctIndex = 2,
                    explanation = "Safeguarding is a critical duty for all London drivers. Suspicious signs must be reported to the police or modern slavery helpline without placing yourself or the victim at risk."
                ),
                MockQuestion(
                    id = 5,
                    question = "Within how many days must a licensed London PHV driver notify TfL if they change their home address?",
                    options = listOf(
                        "Within 7 days",
                        "Within 21 days",
                        "Within 30 days",
                        "Only when renewing the licence"
                    ),
                    correctIndex = 1,
                    explanation = "Under TfL licensing conditions, drivers must notify TfL in writing of any change of address within 21 days."
                ),
                MockQuestion(
                    id = 6,
                    question = "What must a driver do if a passenger accidentally leaves property (lost property) in their vehicle?",
                    options = listOf(
                        "Keep it if not claimed within 24 hours",
                        "Hand it in to the operator or follow TfL lost property procedure within the specified timeframe",
                        "Sell it to cover the journey fare",
                        "Leave it on the pavement outside the destination"
                    ),
                    correctIndex = 1,
                    explanation = "Drivers must check their vehicles after every trip and hand any lost property into their operating centre or agreed police/TfL procedure as soon as practicable."
                ),
                MockQuestion(
                    id = 7,
                    question = "Can a driver use a handheld mobile phone while waiting at a red traffic light?",
                    options = listOf(
                        "Yes, because the vehicle is stationary",
                        "No, holding and using a phone while in control of a vehicle, even when stopped at lights, is strictly illegal in the UK",
                        "Yes, if only sending a quick text to the operator",
                        "Yes, if using speakerphone"
                    ),
                    correctIndex = 1,
                    explanation = "UK law strictly prohibits holding a phone while driving, which includes waiting at red lights or in traffic queues. 6 penalty points and a £200 fine apply."
                ),
                MockQuestion(
                    id = 8,
                    question = "What is the legal blood alcohol limit for drivers in England and Wales?",
                    options = listOf(
                        "0 micrograms per 100ml of breath",
                        "35 micrograms per 100ml of breath (though zero tolerance is recommended for professional drivers)",
                        "80 micrograms per 100ml of breath",
                        "50 micrograms per 100ml of breath"
                    ),
                    correctIndex = 1,
                    explanation = "The legal limit in England is 35 micrograms of alcohol per 100 milliliters of breath. Professional drivers are expected to drive with zero alcohol."
                ),
                MockQuestion(
                    id = 9,
                    question = "Which of the following is considered a 'protected characteristic' under the Equality Act 2010?",
                    options = listOf(
                        "Disability, race, religion, sex and sexual orientation",
                        "Favourite football team",
                        "Type of mobile phone used",
                        "Level of personal income"
                    ),
                    correctIndex = 0,
                    explanation = "The Equality Act 2010 protects 9 characteristics: age, disability, gender reassignment, marriage/civil partnership, pregnancy/maternity, race, religion/belief, sex, sexual orientation."
                ),
                MockQuestion(
                    id = 10,
                    question = "What should you do if an aggressive passenger begins swearing and demanding you drive faster than the speed limit?",
                    options = listOf(
                        "Speed up to prevent the passenger getting angrier",
                        "Remain calm, politely and firmly state that speeding is against the law, and pull over safely if safety is compromised",
                        "Shout back and accelerate",
                        "Abandon the car in the middle lane of the road"
                    ),
                    correctIndex = 1,
                    explanation = "Never compromise safety or break traffic law due to passenger pressure. Remain professional, explain the legal limit, and contact the operator or police if needed."
                )
            )
        ),
        MockQuiz(
            id = "mock-cscs-health",
            title = "CSCS Health & Safety (Green Card) Practice Test",
            category = CourseCategory.CONSTRUCTION,
            description = "10 CITB-aligned questions covering site hazards, PPE, manual handling, and emergency signs.",
            passingScorePercent = 80,
            questions = listOf(
                MockQuestion(
                    id = 1,
                    question = "What does a blue circular safety sign with a white symbol indicate on a UK construction site?",
                    options = listOf(
                        "Prohibition (Do not do)",
                        "Mandatory action (You MUST do, e.g. wear hard hat or eye protection)",
                        "Warning of a danger or hazard",
                        "Safe condition / emergency exit"
                    ),
                    correctIndex = 1,
                    explanation = "Blue circles are Mandatory signs indicating actions that must be adhered to (such as 'Eye protection must be worn' or 'Safety footwear required')."
                ),
                MockQuestion(
                    id = 2,
                    question = "What does the abbreviation 'COSHH' stand for in UK workplace safety?",
                    options = listOf(
                        "Control of Substances Hazardous to Health",
                        "Certificate of Safety & Handling Hazards",
                        "Care of Scaffolding & Heavy Hardware",
                        "Construction Operations & Site Health"
                    ),
                    correctIndex = 0,
                    explanation = "COSHH stands for Control of Substances Hazardous to Health Regulations 2002, governing dust, chemicals, fumes, and solvents."
                ),
                MockQuestion(
                    id = 3,
                    question = "What is the recommended maximum safe lifting technique when lifting a heavy box from the ground?",
                    options = listOf(
                        "Keep legs straight and bend your back fully",
                        "Bend your knees, keep your back straight, hold the load close to your waist, and lift smoothly with your leg muscles",
                        "Twist quickly while lifting to save time",
                        "Always lift with one hand only"
                    ),
                    correctIndex = 1,
                    explanation = "Proper manual handling requires bending the knees, keeping a straight spine, and using leg power while keeping the center of gravity close to the body."
                ),
                MockQuestion(
                    id = 4,
                    question = "Which type of fire extinguisher is colored with a RED band and is suitable for wood, paper, and textile fires?",
                    options = listOf(
                        "Water extinguisher (Class A)",
                        "Carbon Dioxide (Black band)",
                        "Foam extinguisher (Cream band)",
                        "Dry Powder (Blue band)"
                    ),
                    correctIndex = 0,
                    explanation = "All-red or red-banded extinguishers are Water extinguishers, suitable for Class A combustible materials like wood and paper (never use on electrical fires)."
                ),
                MockQuestion(
                    id = 5,
                    question = "What should you do immediately if you discover a dangerous hazard on site that could cause immediate injury to someone?",
                    options = listOf(
                        "Wait until the end of the shift to mention it",
                        "Warn anyone in danger, stop the work safely, and report it immediately to your site supervisor or safety officer",
                        "Cover it up with a piece of cardboard",
                        "Ignore it if it is not your personal trade"
                    ),
                    correctIndex = 1,
                    explanation = "Everyone on site has a legal duty under the Health and Safety at Work Act to protect themselves and others by reporting and addressing imminent hazards promptly."
                ),
                MockQuestion(
                    id = 6,
                    question = "What color is the CSCS card issued to general construction labourers after passing Level 1 H&S and CITB test?",
                    options = listOf(
                        "Blue (Skilled Worker)",
                        "Green (Labourer)",
                        "Gold (Supervisor)",
                        "Black (Manager)"
                    ),
                    correctIndex = 1,
                    explanation = "The CSCS Labourer card is Green, valid for 5 years upon passing the Level 1 Health and Safety award and CITB HS&E test."
                ),
                MockQuestion(
                    id = 7,
                    question = "What is the standard safe low-voltage power supply used for hand tools on UK building sites?",
                    options = listOf(
                        "240 Volts AC (domestic mains)",
                        "110 Volts (yellow cables and transformers)",
                        "415 Volts 3-phase",
                        "12 Volts DC"
                    ),
                    correctIndex = 1,
                    explanation = "UK construction sites mandate 110V reduced low-voltage systems (yellow transformers and sockets) to minimize electric shock fatalities."
                ),
                MockQuestion(
                    id = 8,
                    question = "What does a yellow triangular sign with a black border indicate?",
                    options = listOf(
                        "Warning / caution of hazard (e.g. overhead crane, corrosive substance)",
                        "Mandatory requirement",
                        "First aid station",
                        "No entry"
                    ),
                    correctIndex = 0,
                    explanation = "Yellow triangles are Hazard Warning signs warning of risks such as slippery surfaces, toxic chemicals, or moving plant machinery."
                ),
                MockQuestion(
                    id = 9,
                    question = "Under RIDDOR regulations, what must an employer report to the Health and Safety Executive (HSE)?",
                    options = listOf(
                        "Late employee arrivals",
                        "Work-related fatalities, specified major injuries, and occupational diseases",
                        "Employee meal break choices",
                        "Tool depreciation costs"
                    ),
                    correctIndex = 1,
                    explanation = "RIDDOR (Reporting of Injuries, Diseases and Dangerous Occurrences Regulations) requires reporting deaths, specified fractures, amputations, and dangerous occurrences."
                ),
                MockQuestion(
                    id = 10,
                    question = "What should you check before using a portable ladder on site?",
                    options = listOf(
                        "That it has been inspected, has a valid inspection tag, is placed on level ground at a 1:4 angle, and is secured/tied",
                        "Only that it looks clean",
                        "That it is made of solid steel",
                        "Nothing, all ladders are automatically safe"
                    ),
                    correctIndex = 0,
                    explanation = "Ladders must be inspected for damaged rungs/stiles, set at the 1 in 4 rule (75 degrees), and tied or secured at top and bottom to prevent slippage."
                )
            )
        ),
        MockQuiz(
            id = "mock-life-in-uk",
            title = "Life in the UK & ESOL Practice Test",
            category = CourseCategory.ESOL,
            description = "10 questions on British history, institutions, and daily communication for citizenship candidates.",
            passingScorePercent = 80,
            questions = listOf(
                MockQuestion(
                    id = 1,
                    question = "What historical document signed by King John in 1215 established that everyone, including the monarch, is subject to the law?",
                    options = listOf(
                        "The Bill of Rights",
                        "Magna Carta (The Great Charter)",
                        "The Act of Union",
                        "The Domesday Book"
                    ),
                    correctIndex = 1,
                    explanation = "Magna Carta, sealed in 1215 at Runnymede, is a cornerstone of the British constitution establishing the rule of law and trial by jury."
                ),
                MockQuestion(
                    id = 2,
                    question = "What are the two chambers of the UK Parliament at Westminster?",
                    options = listOf(
                        "The House of Commons and the House of Lords",
                        "The Senate and the Congress",
                        "The National Assembly and the Supreme Court",
                        "The Privy Council and the High Court"
                    ),
                    correctIndex = 0,
                    explanation = "The UK Parliament consists of the elected House of Commons, the House of Lords, and the Monarch."
                ),
                MockQuestion(
                    id = 3,
                    question = "What is the patron saint of England celebrated on 23rd April?",
                    options = listOf(
                        "St Andrew",
                        "St George",
                        "St Patrick",
                        "St David"
                    ),
                    correctIndex = 1,
                    explanation = "St George is the patron saint of England. St Andrew represents Scotland, St David Wales, and St Patrick Northern Ireland."
                ),
                MockQuestion(
                    id = 4,
                    question = "Who was the British Prime Minister during the majority of the Second World War famous for his inspiring speeches?",
                    options = listOf(
                        "Clement Attlee",
                        "Winston Churchill",
                        "Neville Chamberlain",
                        "Harold Wilson"
                    ),
                    correctIndex = 1,
                    explanation = "Winston Churchill led Great Britain from 1940 to 1945, rallying the nation with resolute leadership during the Battle of Britain and WWII."
                ),
                MockQuestion(
                    id = 5,
                    question = "At what age do UK citizens become eligible to vote in general elections?",
                    options = listOf(
                        "16 years old",
                        "18 years old",
                        "21 years old",
                        "25 years old"
                    ),
                    correctIndex = 1,
                    explanation = "In UK general parliamentary elections, the legal voting age is 18."
                ),
                MockQuestion(
                    id = 6,
                    question = "Which UK institution provides free medical treatment funded through general taxation?",
                    options = listOf(
                        "The British Medical Association (BMA)",
                        "The National Health Service (NHS)",
                        "The Department of Social Security",
                        "The Red Cross UK"
                    ),
                    correctIndex = 1,
                    explanation = "The National Health Service (NHS) was established in 1948 by Aneurin Bevan to provide healthcare free at the point of delivery."
                ),
                MockQuestion(
                    id = 7,
                    question = "What is the capital city of Scotland?",
                    options = listOf(
                        "Glasgow",
                        "Edinburgh",
                        "Aberdeen",
                        "Cardiff"
                    ),
                    correctIndex = 1,
                    explanation = "Edinburgh is the historic capital of Scotland, home to Edinburgh Castle and the Scottish Parliament."
                ),
                MockQuestion(
                    id = 8,
                    question = "Which flower is traditionally worn on Remembrance Day (11th November) in Britain?",
                    options = listOf(
                        "Red Rose",
                        "Red Poppy",
                        "Daffodil",
                        "Thistle"
                    ),
                    correctIndex = 1,
                    explanation = "The red poppy is worn to commemorate military personnel and civilians who lost their lives in war and conflicts."
                ),
                MockQuestion(
                    id = 9,
                    question = "What is the official currency of the United Kingdom?",
                    options = listOf(
                        "Euro",
                        "Pound Sterling (£ / GBP)",
                        "Dollar",
                        "Crown"
                    ),
                    correctIndex = 1,
                    explanation = "The official currency is the British Pound Sterling (GBP)."
                ),
                MockQuestion(
                    id = 10,
                    question = "What phone number should you dial in the UK in an immediate emergency to reach Police, Ambulance, or Fire Brigade?",
                    options = listOf(
                        "911",
                        "999 (or 112)",
                        "101",
                        "111"
                    ),
                    correctIndex = 1,
                    explanation = "999 is the national emergency number in the UK (112 also routes to the same service). 101 is for non-emergencies and 111 is for non-emergency NHS health advice."
                )
            )
        )
    )
}
