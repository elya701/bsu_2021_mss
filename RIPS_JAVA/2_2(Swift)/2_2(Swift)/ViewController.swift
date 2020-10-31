//
//  ViewController.swift
//  2_2
//
//  Created by Roma on 10/31/20.
//  Copyright © 2020 Roma. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    

    var individuals = [Individual]()
    let tableView = UITableView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.appendIndividual(name: "Roman", basicIncome: 0, copyrightIncome: 200, sellIncome: 300, presentIncome: 150, internationalIncome: 200)
        self.appendIndividual(name: "Kirll", basicIncome: 70, copyrightIncome: 130, sellIncome: 290, presentIncome: 1239, internationalIncome: 37)
        self.appendIndividual(name: "Evgenii", basicIncome: 120, copyrightIncome: 56, sellIncome: 174, presentIncome: 340, internationalIncome: 560)
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cellid")
        self.view.addSubview(tableView)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.leftAnchor.constraint(equalTo: self.view.leftAnchor).isActive = true
        tableView.rightAnchor.constraint(equalTo: self.view.rightAnchor).isActive = true
        tableView.topAnchor.constraint(equalTo: self.view.topAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor).isActive = true
        self.tableView.reloadData()
    }
    
    func appendIndividual(name: String?, basicIncome: Double?, copyrightIncome: Double?, sellIncome: Double?, presentIncome: Double?, internationalIncome: Double?) {
        let individual = Individual(name: name, basicIncome: basicIncome, copyrightIncome: copyrightIncome, sellIncome: sellIncome, presentIncome: presentIncome, internationalIncome: internationalIncome)
        self.individuals.append(individual)
        print(individuals.count)
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return individuals.count
    }
       
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "cellid") as! UITableViewCell
            cell.textLabel?.text = individuals[indexPath.row].name
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let vc = DetailViewController(individual: individuals[indexPath.row])
        vc.modalPresentationStyle = .fullScreen
        self.present(vc, animated: true, completion: nil)
    }
}

public class Individual {

    var name: String?
    var basicTax: Double?
    var copyrightTax: Double?
    var sellTax: Double?
    var presentTax: Double?
    var internationalTax: Double?
    
    init(name: String?, basicIncome: Double?, copyrightIncome: Double?, sellIncome: Double?, presentIncome: Double?, internationalIncome: Double?) {
        self.name = name;
        self.basicTax = (basicIncome ?? 0) *  0.08;
        self.copyrightTax = (copyrightIncome ?? 0) * 0.12;
        self.sellTax = (sellIncome ?? 0) * 0.2;
        self.presentTax = (presentIncome ?? 0) * 0;
        self.internationalTax = (internationalIncome ?? 0) * 0.04;
    }

    public func getTotalTax() -> Double {
        return (self.basicTax! + self.copyrightTax! + self.sellTax! + self.presentTax! + self.internationalTax!);
    }

    public func getBasicTax() -> Double {
        return self.basicTax!;
    }

    public func getCopyrightTax() -> Double {
        return self.copyrightTax!;
    }

    public func getSellTax() -> Double {
        return self.sellTax!;
    }

    public func getPresentTax() -> Double {
        return self.presentTax!;
    }

    public func getInternationalTax() -> Double {
        return self.internationalTax!;
    }

    public func addBasicIncome(income: Double, year: Int) {
        if (year == 2020) {
            self.basicTax! += income * 0.08;
        }
    }

    public func addCopyrightIncome(income: Double, year: Int) {
        if (year == 2020) {
            self.basicTax! += income * 0.12;
        }
    }

    public func addSellIncome(income: Double, year: Int) {
        if (year == 2020) {
            self.basicTax! += income * 0.2;
        }
    }

    public func addPresentIncome(income: Double, year: Int) {
        if (year == 2020) {
            self.basicTax! += income * 0;
        }
    }

    public func addInternationalIncome(income: Double, year: Int) {
        if (year == 2020) {
            self.basicTax! += income * 0.04;
        }
    }
}

class DetailViewController: UIViewController {
    

    var individual: Individual?
   
    let labelOne = UILabel()
    let labelTwo = UILabel()
    let labelThree = UILabel()
    let labelFourth = UILabel()
    let labelFive = UILabel()
    let labelSix = UILabel()
    let labelSeven = UILabel()
    
    let button = UIButton()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = .white
        
        self.view.addSubview(labelOne)
        labelOne.translatesAutoresizingMaskIntoConstraints = false
        labelOne.text = "Name: \((individual?.name)!)"
        labelOne.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelOne.topAnchor.constraint(equalTo: self.view.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(labelTwo)
        labelTwo.translatesAutoresizingMaskIntoConstraints = false
        labelTwo.text = "Basic Tax: \((individual?.basicTax)!) USD"
        labelTwo.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelTwo.topAnchor.constraint(equalTo: self.labelOne.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(labelThree)
        labelThree.translatesAutoresizingMaskIntoConstraints = false
        labelThree.text = "Copyright Tax: \((individual?.copyrightTax)!) USD"
        labelThree.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelThree.topAnchor.constraint(equalTo: self.labelTwo.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(labelFourth)
        labelFourth.translatesAutoresizingMaskIntoConstraints = false
        labelFourth.text = "International Tax: \((individual?.internationalTax)!) USD"
        labelFourth.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelFourth.topAnchor.constraint(equalTo: self.labelThree.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(labelFive)
        labelFive.translatesAutoresizingMaskIntoConstraints = false
        labelFive.text = "Sell Tax: \((individual?.sellTax)!) USD"
        labelFive.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelFive.topAnchor.constraint(equalTo: self.labelFourth.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(labelSix)
        labelSix.translatesAutoresizingMaskIntoConstraints = false
        labelSix.text = "Present Tax: \((individual?.presentTax)!) USD"
        labelSix.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelSix.topAnchor.constraint(equalTo: self.labelFive.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(labelSeven)
        labelSeven.translatesAutoresizingMaskIntoConstraints = false
        labelSeven.text = "Total tax: \((individual?.getTotalTax())!) USD"
        labelSeven.leftAnchor.constraint(equalTo: self.view.leftAnchor, constant: 20).isActive = true
        labelSeven.topAnchor.constraint(equalTo: self.labelSix.topAnchor, constant: 40).isActive = true
        
        self.view.addSubview(button)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("Dissmis", for: .normal)
        button.setTitleColor(.white, for: .normal)
        button.backgroundColor = .systemBlue
        button.addTarget(self, action: #selector(dismiss(_:)), for: .touchUpInside)
        button.centerXAnchor.constraint(equalTo: self.view.centerXAnchor).isActive = true
        button.layer.cornerRadius = 20
        button.layer.masksToBounds = true
        button.topAnchor.constraint(equalTo: self.labelSeven.topAnchor, constant: 30).isActive = true
        button.widthAnchor.constraint(equalToConstant: 160).isActive = true
        button.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
    
    init(individual: Individual) {
        super.init(nibName: nil, bundle: nil)
        self.individual = individual
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    @objc func dismiss(_ sender : UIButton) {
        self.dismiss(animated: true, completion: nil)
    }
}


