package fr.univtours.s6_projet.controller.admin;

import fr.univtours.s6_projet.entity.PanierOrder;
import fr.univtours.s6_projet.entity.Status;
import fr.univtours.s6_projet.repository.CommercantRepository;
import fr.univtours.s6_projet.repository.PanierOrderRepository;
import fr.univtours.s6_projet.repository.PanierRepository;
import fr.univtours.s6_projet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PanierRepository panierRepository;

	@Autowired
	private PanierOrderRepository panierOrderRepository;

	@Autowired
	private CommercantRepository commercantRepository;

	@RequestMapping("/admin")
	public String index(Model model) {

		List<PanierOrder> orders = panierOrderRepository.findAll();

		for (PanierOrder order : orders) {
			if (order.getStatus() != Status.EXPIRED && order.getStatus() != Status.DELIVERED) {
				if (order.getPanier().getValidUntil().before(new Date())) {
					order.setStatus(Status.EXPIRED);
					panierOrderRepository.saveAndFlush(order);
				}
			}
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		Date lastWeek = calendar.getTime();

		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, -1);
		Date lastMonth = calendar.getTime();

		Long nbPanier = panierRepository.count();
		Long nbPanierDelivered = panierRepository.countByDevilered(Status.DELIVERED);
		Long nbPanierDeliveredThisWeek = panierRepository.countByDeliveredSince(Status.DELIVERED, lastWeek);
		Long nbPanierDeliveredThisMonth = panierRepository.countByDeliveredSince(Status.DELIVERED, lastMonth);
		Long nbPanierExpired = panierRepository.countByOrder_Status(Status.EXPIRED);
		Long nbPanierTooLate = panierRepository.countByOrderIsNullAndValidUntilBefore(new Date());
		Long nbPanierWasted = nbPanierExpired + nbPanierTooLate;

		Long nbUser = userRepository.count();
		Long nbUserThisWeek = userRepository.countSince(lastWeek);
		Long nbUserThisMonth = userRepository.countSince(lastMonth);

		Long nbCommercant = commercantRepository.count();

		model.addAttribute("nbPanier", nbPanier);
		model.addAttribute("nbPanierDelivered", nbPanierDelivered);
		model.addAttribute("nbPanierDeliveredThisWeek", nbPanierDeliveredThisWeek);
		model.addAttribute("nbPanierDeliveredThisMonth", nbPanierDeliveredThisMonth);
		model.addAttribute("nbPanierWasted", nbPanierWasted);
		model.addAttribute("nbUser", nbUser);
		model.addAttribute("nbUserThisWeek", nbUserThisWeek);
		model.addAttribute("nbUserThisMonth", nbUserThisMonth);
		model.addAttribute("nbCommercant", nbCommercant);

		return "/admin/index";
	}
}
