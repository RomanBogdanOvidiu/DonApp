package com.web.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Lob;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.config.AppConfig;
import com.users.model.ContactData;
import com.users.model.Donation;
import com.users.model.EmailSender;
import com.users.model.Imagine;
import com.users.model.Search;
import com.users.model.User;
import com.users.model.UserRole;
import com.users.service.DonationService;
import com.users.service.ImagineService;
import com.users.service.UserRoleService;
import com.users.service.UserService;
import com.utils.HibernateUtils;

@Controller
// @SessionAttributes({"donations"})
public class MainController {
	@Autowired
	private ApplicationContext appContext;
	@Autowired
	ServletContext servletContext;

	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ImagineService imagineService;

	@Autowired
	protected UserRoleService userRoleService;

	@Autowired
	protected DonationService donationService;

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Hospital Application");
		model.addObject("message", "Main Page");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)

	public ModelAndView signUp() {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("message", "Create new user account");
		User newUser = new User();
		forSignUp.addObject("user", newUser);
		forSignUp.setViewName("signUp");
		return forSignUp;

	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String signUp(User u1) {

		if (u1.getFirstName().trim() != "" && u1.getLastName().trim() != "" && u1.getUsername().trim() != ""
				&& u1.getPassword().trim() != "" && u1.getContactInfo().trim() != "" && u1.getEmail().trim() != ""
				&& u1.getContactInfo().length() == 10 && u1.getEmail().contains("@")) {

			Set<UserRole> roleSet = new HashSet<UserRole>(0);
			List<User> users = userService.findAll();

			for (User us : users) {
				if (us.getUsername().equals(u1.getUsername())) {
					return "redirect:/wrong";
				}
			}

			UserRole uR = new UserRole(u1, "ROLE_ADMIN");
			roleSet.add(uR);

			u1.setUserRole(roleSet);
			u1.setEnabled(true);

			// System.out.println(u1.getUsername());
			PasswordEncoder encoder = new BCryptPasswordEncoder();
			String hash = encoder.encode(u1.getPassword());
			u1.setPassword(hash);

			userService.insert(u1);
			userService.insert2(uR);

			return "redirect:/sucCreated";
		}
		return "redirect:/wrong";
	}

	/////////////////////// MESSAGES///////////////////////////////

	@Transactional
	@RequestMapping(value = "/sucCreated", method = RequestMethod.GET)
	public String succesfullOperation() {
		return "/sucCreated";

	}

	@Transactional
	@RequestMapping(value = "/insuf", method = RequestMethod.GET)
	public String moneyLess() {
		return "/insuf";

	}

	@RequestMapping(value = { "/check" }, method = RequestMethod.GET)
	public @ResponseBody String checkConsultations() {
		String data = "";
		// List<Consultation> consultations = consultationService.findAll();
		// for (Consultation c : consultations) {
		// if (c.isChecked() == false) {
		// data = "Patient " + data + c.getClient().getFirstName() + " " +
		// c.getClient().getLastName()
		// + " is waiting ";
		//
		// consultationService.save(c);
		// }
		// }
		return data;
	}

	@Transactional
	@RequestMapping(value = "/wrong", method = RequestMethod.GET)
	public String wrong() {
		return "/wrong";

	}

	///////////// DONATIONS CONSTRU
	@RequestMapping(value = "/donation/{username}", method = RequestMethod.GET)
	public ModelAndView createDonationGet(@PathVariable("username") String username) {
		ModelAndView forSignUp = new ModelAndView();
		forSignUp.addObject("title", "Donation Application");
		forSignUp.addObject("message", "Add new donation");
		User user = userService.findByUsername(username);

		Donation donation = new Donation();

		donation.setUser(user);
		

		forSignUp.addObject("donation", donation);
		forSignUp.setViewName("donation");
		return forSignUp;
	}

	@Transactional
	@RequestMapping(value = "/donation/{username}", method = RequestMethod.POST)
	public String createDonationPost(Donation donation, @PathVariable("username") String username,
			@RequestParam(value = "image", required = false) MultipartFile image) {
		
		Imagine img=new Imagine();
		
		if(!donation.getCity().trim().equals("") && !donation.getDonationsDesc().trim().equals("")
				&& !donation.getDonationsTitle().trim().equals("")){
		User u = userService.findByUsername(username);
		donation.setUser(u);
		donationService.insert(donation);

		    byte[] buf = new byte[4096];
		try {
			buf=image.getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		img.setDonation(donation);
		img.setImgB(buf);
		
		donation.getImg().add(img);		
		imagineService.save(img);
		
//		Session session = new AppConfig().sessionFactory().getCurrentSession();
//		byte[] byteArr = null;
//		try {
//			byteArr = image.getBytes();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Blob blob = Hibernate.getLobCreator(session).createBlob(byteArr);
//		
//		//Save method will be call here 
	
//		Imagine imagine = new Imagine();
//		imagine.setImgB(blob);
//		imagine.setDonation(donation);
//		donation.getImg().add(imagine);
		
		return "redirect:/user/donation/";}
		return "redirect:/wrong";
	}

	@RequestMapping(value = "/user/donation", method = RequestMethod.GET)
	public ModelAndView fullList() {
		ModelAndView forSignUp = new ModelAndView();

		List<Donation> donation = donationService.findAll();
		forSignUp.addObject("donations", donation);
		forSignUp.addObject("search", new Search());
		forSignUp.setViewName("donationsList");

		return forSignUp;

	}

	@RequestMapping(value = "/user/donation/{username}", method = RequestMethod.GET)
	public ModelAndView connectDonationToUser(@PathVariable("username") String username) {
		ModelAndView forSignUp = new ModelAndView();
		User user = userService.findByUsername(username);
		List<Donation> donation = donationService.findByUser(user);
		forSignUp.addObject("donationes", donation);
		forSignUp.setViewName("personalDonation");

		return forSignUp;

	}

	@Transactional
	@RequestMapping(value = "/user/donation/delete/{id}", method = RequestMethod.GET)
	public String deleteDonation(@PathVariable("id") Integer id) {

		Donation don = donationService.findByDonationsId(id);
		User user = userService.findByUsername(don.getUser().getUsername());
		List<Donation> donations = new ArrayList<Donation>();
		for (Donation donation : user.getDonation()) {
			if (donation.getDonationsId() != don.getDonationsId()) {
				donations.add(donation);
			}
		}
		user.setDonation(donations);
		userService.insert(user);
		donationService.delete(don);

		return "redirect:/user/donation/" + user.getUsername();
	}

	@Transactional
	@RequestMapping(value = "/user/donation/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editAccount(@PathVariable("id") Integer id) {
		Donation don = donationService.findByDonationsId(id);

		ModelAndView model = new ModelAndView();
		model.addObject("donation", don);
		model.setViewName("donation");
		return model;
	}

	@Transactional
	@RequestMapping(value = "/user/donation/view/{id}", method = RequestMethod.GET)
	public ModelAndView viewDonation(@PathVariable("id") Integer id) {

		ModelAndView model = new ModelAndView();
		ContactData contactData = new ContactData();
		Donation don = donationService.findByDonationsId(id);
		
	//	System.out.println("ITS MY LIFE : "+don.getImg());
		model.addObject("donation", don);
		model.addObject("contactData", contactData);
		model.setViewName("donationDetails");
		return model;
	}

	private List<Donation> searchResult(String donationsTitle) {
		List<Donation> data = donationService.findAll();

		List<Donation> result = new ArrayList<Donation>();
		// iterate a list and filter by tagName
		for (Donation don : data) {
			if (don.getDonationsTitle().contains(donationsTitle)) {
				result.add(don);
			}
		}

		return result;
	}

	@RequestMapping(value = "/getDonations", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Donation> getDonations(@RequestParam(value = "donationsTitle") String donationsTitle,
			Model map) {
		List<Donation> result = searchResult(donationsTitle);
		map.addAttribute("donations", result);
		return result;

	}

	@RequestMapping(value = "/user/donation/search", method = RequestMethod.POST)
	public ModelAndView searchForm(Search obj) {
		ModelAndView forSignUp = new ModelAndView();

		List<Donation> donation = donationService.findByDonationsTitle(obj.getDonationsTitle());
		forSignUp.addObject("donations", donation);
		forSignUp.addObject("search", new Search());
		forSignUp.setViewName("donationsList");

		return forSignUp;

	}


	@RequestMapping(value = "/client/{username}", method = RequestMethod.GET)
	public ModelAndView clientDetails(@PathVariable("username")
	 String username) {
		ModelAndView forSignUp = new ModelAndView();
		User user = userService.findByUsername(username);
		forSignUp.addObject("clients", user);
		forSignUp.setViewName("client");
		return forSignUp;
	}
	
	
	
	
	 ///user/donation/view/4
	 @RequestMapping(value = "user/donation/view/contact/{username}", method =
	 RequestMethod.POST)
	 public String contactPost(ContactData contactData,@PathVariable("username")
	 String username  ,BindingResult result) {
	
	

		 User u=userService.findByUsername(username);
		 
	 
	 String to=u.getEmail();
	 String emailAdr = contactData.getEmail();
	 String message = contactData.getMessage();
	 message = message + "\n NAME: " + contactData.getName() + contactData.getEmail();
	 String subject = contactData.getSubject();
	
	 //WebApplicationContext ctx = appContext.getRequiredWebApplicationContext(servletContext);
	 
	// EmailSender mm = (EmailSender) appContext.getBean("mailmail");
	
	 
//	 mm.sendMail("ichallengeyu@gmail.com", to, subject, message);
	
	
	
	 return "redirect:/user/donation";
	 }

}
