package com.codaconsultancy.model;

import com.codaconsultancy.dao.*;
import com.codaconsultancy.entities.Address;
import com.codaconsultancy.entities.Client;
import com.codaconsultancy.entities.ClientDetail;
import com.codaconsultancy.entities.Skill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SkillManagerApp {

    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getFactory();
        daoFactory.newClientDAO().beginTransaction();
        ClientDAO clientDAO = daoFactory.newClientDAO();
        ClientDetailDAO clientDetailDAO = daoFactory.newClientDetailDAO();
        AddressDAO addressDAO = daoFactory.newAddressDAO();
        SkillDAO skillDAO = daoFactory.newSkillDAO();

        Client client = new Client();
        client.setUsername("steve");
        client.setPassword("123qwerty");
        ClientDetail clientDetail = new ClientDetail();
        clientDetail.setFirstName("Stephen");
        clientDetail.setMiddleName("Wilhelm");
        clientDetail.setLastName("Blance");
        clientDetail.setPasswordHint("3 numbers and some letters");
        clientDetail.setRegistrationDate(newDate("17/12/2014"));
        clientDetail.setVerificationDate(newDate("18/12/2014"));
        client.setClientDetail(clientDetail);
        Address address = new Address();
        address.setAddressLine1("123 Street Road");
        address.setAddressLine2("Comfy Estate");
        address.setCity("Warmville");
        address.setState("Fife");
        address.setCode("KY12 6XX");
        address.setCountry("Scotland");
        address.setClient(client);
        List<Address> addresses = new ArrayList<Address>();
        addresses.add(address);
        client.setAddresses(addresses);
        Skill javaEE = new Skill();
        javaEE.setName("Java EE");
        Skill oracle = new Skill();
        oracle.setName("Oracle PL/SQL");
        Skill rubyOnRails = new Skill();
        rubyOnRails.setName("Ruby on Rails");
        List<Skill> skills = new ArrayList<Skill>();
        skills.add(javaEE);
        skills.add(oracle);
        skills.add(rubyOnRails);
        client.setSkills(skills);

        clientDAO.save(client);
        clientDetailDAO.save(clientDetail);
        addressDAO.save(address);
        skillDAO.save(javaEE);
        skillDAO.save(oracle);
        skillDAO.save(rubyOnRails);

        daoFactory.newClientDAO().commitTransaction();
    }

    private static Date newDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
