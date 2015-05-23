-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 23 Maj 2015, 21:35
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `zpi_tours`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `miasta`
--

CREATE TABLE IF NOT EXISTS `miasta` (
  `id_miasta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nazwa_miasta` varchar(40) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`id_miasta`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=916 ;

--
-- Zrzut danych tabeli `miasta`
--

INSERT INTO `miasta` (`id_miasta`, `nazwa_miasta`) VALUES
(1, 'Aleksandr�w Kujawski'),
(2, 'Aleksandr�w ��dzki'),
(3, 'Alwernia'),
(4, 'Andrych�w'),
(5, 'Annopol'),
(6, 'August�w'),
(7, 'Babimost'),
(8, 'Babor�w'),
(9, 'Baran�w Sandomierski'),
(10, 'Barcin'),
(11, 'Barczewo'),
(12, 'Bardo'),
(13, 'Barlinek'),
(14, 'Bartoszyce'),
(15, 'Barwice'),
(16, 'Be�chat�w'),
(17, 'Be��yce'),
(18, 'B�dzin'),
(19, 'Bia�a'),
(20, 'Bia�a Piska'),
(21, 'Bia�a Podlaska'),
(22, 'Bia�a Rawska'),
(23, 'Bia�obrzegi'),
(24, 'Bia�ogard'),
(25, 'Bia�y B�r'),
(26, 'Bia�ystok'),
(27, 'Biecz'),
(28, 'Bielawa'),
(29, 'Bielsk Podlaski'),
(30, 'Bielsko-Bia�a'),
(31, 'Bieru�'),
(32, 'Bierut�w'),
(33, 'Bie�u�'),
(34, 'Bi�goraj'),
(35, 'Biskupiec'),
(36, 'Bisztynek'),
(37, 'Blachownia'),
(38, 'B�aszki'),
(39, 'B�a�owa'),
(40, 'B�onie'),
(41, 'Bobolice'),
(42, 'Bobowa'),
(43, 'Bochnia'),
(44, 'Bodzentyn'),
(45, 'Bogatynia'),
(46, 'Boguchwa�a'),
(47, 'Bogusz�w-Gorce'),
(48, 'Bojanowo'),
(49, 'Boles�awiec'),
(50, 'Bolk�w'),
(51, 'Borek Wielkopolski'),
(52, 'Borne Sulinowo'),
(53, 'Braniewo'),
(54, 'Bra�sk'),
(55, 'Brodnica'),
(56, 'Brok'),
(57, 'Brusy'),
(58, 'Brwin�w'),
(59, 'Brzeg'),
(60, 'Brzeg Dolny'),
(61, 'Brzesko'),
(62, 'Brzeszcze'),
(63, 'Brze�� Kujawski'),
(64, 'Brzeziny'),
(65, 'Brzostek'),
(66, 'Brzoz�w'),
(67, 'Buk'),
(68, 'Bukowno'),
(69, 'Busko-Zdr�j'),
(70, 'Bychawa'),
(71, 'Byczyna'),
(72, 'Bydgoszcz'),
(73, 'Bystrzyca K�odzka'),
(74, 'Bytom'),
(75, 'Bytom Odrza�ski'),
(76, 'Byt�w'),
(77, 'Cedynia'),
(78, 'Che�m'),
(79, 'Che�mek'),
(80, 'Che�mno'),
(81, 'Che�m�a'),
(82, 'Ch�ciny'),
(83, 'Chmielnik'),
(84, 'Chocian�w'),
(85, 'Chociwel'),
(86, 'Chocz'),
(87, 'Chodecz'),
(88, 'Chodzie�'),
(89, 'Chojna'),
(90, 'Chojnice'),
(91, 'Chojn�w'),
(92, 'Choroszcz'),
(93, 'Chorzele'),
(94, 'Chorz�w'),
(95, 'Choszczno'),
(96, 'Chrzan�w'),
(97, 'Ciechanowiec'),
(98, 'Ciechan�w'),
(99, 'Ciechocinek'),
(100, 'Cieszan�w'),
(101, 'Cieszyn'),
(102, 'Ci�kowice'),
(103, 'Cybinka'),
(104, 'Czaplinek'),
(105, 'Czarna Bia�ostocka'),
(106, 'Czarna Woda'),
(107, 'Czarne'),
(108, 'Czarnk�w'),
(109, 'Czch�w'),
(110, 'Czechowice-Dziedzice'),
(111, 'Czelad�'),
(112, 'Czempi�'),
(113, 'Czerniejewo'),
(114, 'Czersk'),
(115, 'Czerwie�sk'),
(116, 'Czerwionka-Leszczyny'),
(117, 'Cz�stochowa'),
(118, 'Cz�opa'),
(119, 'Cz�uch�w'),
(120, 'Czy�ew'),
(121, '�miel�w'),
(122, 'Daleszyce'),
(123, 'Dar�owo'),
(124, 'D�bie'),
(125, 'D�browa Bia�ostocka'),
(126, 'D�browa G�rnicza'),
(127, 'D�browa Tarnowska'),
(128, 'Debrzno'),
(129, 'D�bica'),
(130, 'D�blin'),
(131, 'D�bno'),
(132, 'Dobczyce'),
(133, 'Dobiegniew'),
(134, 'Dobra (powiat �obeski)'),
(135, 'Dobra (powiat turecki)'),
(136, 'Dobre Miasto'),
(137, 'Dobrodzie�'),
(138, 'Dobrzany'),
(139, 'Dobrzyca'),
(140, 'Dobrzy� nad Wis��'),
(141, 'Dolsk'),
(142, 'Drawno'),
(143, 'Drawsko Pomorskie'),
(144, 'Drezdenko'),
(145, 'Drobin'),
(146, 'Drohiczyn'),
(147, 'Drzewica'),
(148, 'Dukla'),
(149, 'Duszniki-Zdr�j'),
(150, 'Dyn�w'),
(151, 'Dzia�dowo'),
(152, 'Dzia�oszyce'),
(153, 'Dzia�oszyn'),
(154, 'Dzierzgo�'),
(155, 'Dzier�oni�w'),
(156, 'Dziwn�w'),
(157, 'Elbl�g'),
(158, 'E�k'),
(159, 'Frampol'),
(160, 'Frombork'),
(161, 'Garwolin'),
(162, 'G�bin'),
(163, 'Gda�sk'),
(164, 'Gdynia'),
(165, 'Gi�ycko'),
(166, 'Glinojeck'),
(167, 'Gliwice'),
(168, 'G�og�w'),
(169, 'G�og�w Ma�opolski'),
(170, 'G�og�wek'),
(171, 'G�owno'),
(172, 'G�ubczyce'),
(173, 'G�ucho�azy'),
(174, 'G�uszyca'),
(175, 'Gniew'),
(176, 'Gniewkowo'),
(177, 'Gniezno'),
(178, 'Gogolin'),
(179, 'Golczewo'),
(180, 'Goleni�w'),
(181, 'Golina'),
(182, 'Golub-Dobrzy�'),
(183, 'Go�a�cz'),
(184, 'Go�dap'),
(185, 'Goni�dz'),
(186, 'Gorlice'),
(187, 'Gorz�w �l�ski'),
(188, 'Gorz�w Wielkopolski'),
(189, 'Gostynin'),
(190, 'Gosty�'),
(191, 'Go�cino'),
(192, 'Gozdnica'),
(193, 'G�ra'),
(194, 'G�ra Kalwaria'),
(195, 'G�rowo I�aweckie'),
(196, 'G�rzno'),
(197, 'Grab�w nad Prosn�'),
(198, 'Grajewo'),
(199, 'Grodk�w'),
(200, 'Grodzisk Mazowiecki'),
(201, 'Grodzisk Wielkopolski'),
(202, 'Gr�jec'),
(203, 'Grudzi�dz'),
(204, 'Gryb�w'),
(205, 'Gryfice'),
(206, 'Gryfino'),
(207, 'Gryf�w �l�ski'),
(208, 'Gubin'),
(209, 'Hajn�wka'),
(210, 'Halin�w'),
(211, 'Hel'),
(212, 'Hrubiesz�w'),
(213, 'I�awa'),
(214, 'I�owa'),
(215, 'I��a'),
(216, 'Imielin'),
(217, 'Inowroc�aw'),
(218, 'I�sko'),
(219, 'Iwonicz-Zdr�j'),
(220, 'Izbica Kujawska'),
(221, 'Jab�onowo Pomorskie'),
(222, 'Janikowo'),
(223, 'Janowiec Wielkopolski'),
(224, 'Jan�w Lubelski'),
(225, 'Jarocin'),
(226, 'Jaros�aw'),
(227, 'Jasie�'),
(228, 'Jas�o'),
(229, 'Jastarnia'),
(230, 'Jastrowie'),
(231, 'Jastrz�bie-Zdr�j'),
(232, 'Jawor'),
(233, 'Jaworzno'),
(234, 'Jaworzyna �l�ska'),
(235, 'Jedlicze'),
(236, 'Jedlina-Zdr�j'),
(237, 'Jedwabne'),
(238, 'Jelcz-Laskowice'),
(239, 'Jelenia G�ra'),
(240, 'Jeziorany'),
(241, 'J�drzej�w'),
(242, 'Jordan�w'),
(243, 'J�zef�w (powiat bi�gorajski)'),
(244, 'J�zef�w (powiat otwocki)'),
(245, 'Jutrosin'),
(246, 'Kalety'),
(247, 'Kalisz'),
(248, 'Kalisz Pomorski'),
(249, 'Kalwaria Zebrzydowska'),
(250, 'Ka�uszyn'),
(251, 'Kamienna G�ra'),
(252, 'Kamie� Kraje�ski'),
(253, 'Kamie� Pomorski'),
(254, 'Kamie�sk'),
(255, 'Ka�czuga'),
(256, 'Karczew'),
(257, 'Kargowa'),
(258, 'Karlino'),
(259, 'Karpacz'),
(260, 'Kartuzy'),
(261, 'Katowice'),
(262, 'Kazimierz Dolny'),
(263, 'Kazimierza Wielka'),
(264, 'K�ty Wroc�awskie'),
(265, 'Kcynia'),
(266, 'K�dzierzyn-Ko�le'),
(267, 'K�pice'),
(268, 'K�pno'),
(269, 'K�trzyn'),
(270, 'K�ty'),
(271, 'Kielce'),
(272, 'Kietrz'),
(273, 'Kisielice'),
(274, 'Kleczew'),
(275, 'Kleszczele'),
(276, 'Kluczbork'),
(277, 'K�ecko'),
(278, 'K�obuck'),
(279, 'K�odawa'),
(280, 'K�odzko'),
(281, 'Knur�w'),
(282, 'Knyszyn'),
(283, 'Kobylin'),
(284, 'Koby�ka'),
(285, 'Kock'),
(286, 'Kolbuszowa'),
(287, 'Kolno'),
(288, 'Kolonowskie'),
(289, 'Koluszki'),
(290, 'Ko�aczyce'),
(291, 'Ko�o'),
(292, 'Ko�obrzeg'),
(293, 'Koniecpol'),
(294, 'Konin'),
(295, 'Konstancin-Jeziorna'),
(296, 'Konstantyn�w ��dzki'),
(297, 'Ko�skie'),
(298, 'Koprzywnica'),
(299, 'Korfant�w'),
(300, 'Koronowo'),
(301, 'Korsze'),
(302, 'Kos�wLacki'),
(303, 'Kostrzyn'),
(304, 'Kostrzyn nad Odr�'),
(305, 'Koszalin'),
(306, 'Ko�cian'),
(307, 'Ko�cierzyna'),
(308, 'Kowal'),
(309, 'Kowalewo Pomorskie'),
(310, 'Kowary'),
(311, 'Kozieg�owy'),
(312, 'Kozienice'),
(313, 'Ko�min Wielkopolski'),
(314, 'Ko�uch�w'),
(315, 'K�rnik'),
(316, 'Krajenka'),
(317, 'Krak�w'),
(318, 'Krapkowice'),
(319, 'Krasnobr�d'),
(320, 'Krasnystaw'),
(321, 'Kra�nik'),
(322, 'Krobia'),
(323, 'Krosno'),
(324, 'Krosno Odrza�skie'),
(325, 'Kro�niewice'),
(326, 'Krotoszyn'),
(327, 'Kruszwica'),
(328, 'Krynica Morska'),
(329, 'Krynica-Zdr�j'),
(330, 'Krynki'),
(331, 'Krzanowice'),
(332, 'Krzepice'),
(333, 'Krzeszowice'),
(334, 'Krzywi�'),
(335, 'Krzy� Wielkopolski'),
(336, 'Ksi�� Wielkopolski'),
(337, 'Kudowa-Zdr�j'),
(338, 'Kun�w'),
(339, 'Kutno'),
(340, 'Ku�nia Raciborska'),
(341, 'Kwidzyn'),
(342, 'L�dek-Zdr�j'),
(343, 'Legionowo'),
(344, 'Legnica'),
(345, 'Lesko'),
(346, 'Leszno'),
(347, 'Le�na'),
(348, 'Le�nica'),
(349, 'Lewin Brzeski'),
(350, 'Le�ajsk'),
(351, 'L�bork'),
(352, 'L�dziny'),
(353, 'Libi��'),
(354, 'Lidzbark'),
(355, 'Lidzbark Warmi�ski'),
(356, 'Limanowa'),
(357, 'Lipiany'),
(358, 'Lipno'),
(359, 'Lipsk'),
(360, 'Lipsko'),
(361, 'Lubacz�w'),
(362, 'Luba�'),
(363, 'Lubart�w'),
(364, 'Lubawa'),
(365, 'Lubawka'),
(366, 'Lubie� Kujawski'),
(367, 'Lubin'),
(368, 'Lublin'),
(369, 'Lubliniec'),
(370, 'Lubniewice'),
(371, 'Lubomierz'),
(372, 'Lubo�'),
(373, 'Lubraniec'),
(374, 'Lubsko'),
(375, 'Lw�wek'),
(376, 'Lw�wek �l�ski'),
(377, '�abiszyn'),
(378, '�a�cut'),
(379, '�apy'),
(380, '�asin'),
(381, '�ask'),
(382, '�askarzew'),
(383, '�aszcz�w'),
(384, '�aziska G�rne'),
(385, '�azy'),
(386, '�eba'),
(387, '��czna'),
(388, '��czyca'),
(389, '��knica'),
(390, '�obez'),
(391, '�ob�enica'),
(392, '�och�w'),
(393, '�omianki'),
(394, '�om�a'),
(395, '�osice'),
(396, '�owicz'),
(397, '��d�'),
(398, '�uk�w'),
(399, 'Mak�w Mazowiecki'),
(400, 'Mak�w Podhala�ski'),
(401, 'Malbork'),
(402, 'Ma�ogoszcz'),
(403, 'Ma�omice'),
(404, 'Margonin'),
(405, 'Marki'),
(406, 'Maszewo'),
(407, 'Miasteczko �l�skie'),
(408, 'Miastko'),
(409, 'Micha�owo'),
(410, 'Miech�w'),
(411, 'Miejska G�rka'),
(412, 'Mielec'),
(413, 'Mierosz�w'),
(414, 'Mieszkowice'),
(415, 'Mi�dzyb�rz'),
(416, 'Mi�dzych�d'),
(417, 'Mi�dzylesie'),
(418, 'Mi�dzyrzec Podlaski'),
(419, 'Mi�dzyrzecz'),
(420, 'Mi�dzyzdroje'),
(421, 'Miko�ajki'),
(422, 'Miko��w'),
(423, 'Mikstat'),
(424, 'Milan�wek'),
(425, 'Milicz'),
(426, 'Mi�akowo'),
(427, 'Mi�om�yn'),
(428, 'Mi�os�aw'),
(429, 'Mi�sk Mazowiecki'),
(430, 'Miros�awiec'),
(431, 'Mirsk'),
(432, 'M�awa'),
(433, 'M�ynary'),
(434, 'Modliborzyce'),
(435, 'Mogielnica'),
(436, 'Mogilno'),
(437, 'Mo�ki'),
(438, 'Mor�g'),
(439, 'Mordy'),
(440, 'Mory�'),
(441, 'Mosina'),
(442, 'Mr�gowo'),
(443, 'Mrocza'),
(444, 'Mrozy'),
(445, 'Mszana Dolna'),
(446, 'Mszczon�w'),
(447, 'Murowana Go�lina'),
(448, 'Muszyna'),
(449, 'Mys�owice'),
(450, 'Myszk�w'),
(451, 'Myszyniec'),
(452, 'My�lenice'),
(453, 'My�lib�rz'),
(454, 'Nak�o nad Noteci�'),
(455, 'Na��cz�w'),
(456, 'Namys��w'),
(457, 'Narol'),
(458, 'Nasielsk'),
(459, 'Nekla'),
(460, 'Nidzica'),
(461, 'Niemcza'),
(462, 'Niemodlin'),
(463, 'Niepo�omice'),
(464, 'Nieszawa'),
(465, 'Nisko'),
(466, 'Nowa D�ba'),
(467, 'Nowa Ruda'),
(468, 'Nowa Sarzyna'),
(469, 'Nowa S�l'),
(470, 'Nowe'),
(471, 'Nowe Brzesko'),
(472, 'Nowe Miasteczko'),
(473, 'Nowe Miasto Lubawskie'),
(474, 'Nowe Miasto nad Pilic�'),
(475, 'Nowe Skalmierzyce'),
(476, 'Nowe Warpno'),
(477, 'Nowogard'),
(478, 'Nowogrodziec'),
(479, 'Nowogr�d'),
(480, 'Nowogr�d Bobrza�ski'),
(481, 'Nowy Dw�r Gda�ski'),
(482, 'Nowy Dw�r Mazowiecki'),
(483, 'Nowy S�cz'),
(484, 'Nowy Staw'),
(485, 'Nowy Targ'),
(486, 'Nowy Tomy�l'),
(487, 'Nowy Wi�nicz'),
(488, 'Nysa'),
(489, 'Oborniki'),
(490, 'Oborniki �l�skie'),
(491, 'Obrzycko'),
(492, 'Odolan�w'),
(493, 'Ogrodzieniec'),
(494, 'Okonek'),
(495, 'Olecko'),
(496, 'Olesno'),
(497, 'Oleszyce'),
(498, 'Ole�nica'),
(499, 'Olkusz'),
(500, 'Olsztyn'),
(501, 'Olsztynek'),
(502, 'Olszyna'),
(503, 'O�awa'),
(504, 'Opalenica'),
(505, 'Opat�w'),
(506, 'Opoczno'),
(507, 'Opole'),
(508, 'Opole Lubelskie'),
(509, 'Orneta'),
(510, 'Orzesze'),
(511, 'Orzysz'),
(512, 'Osieczna'),
(513, 'Osiek'),
(514, 'Ostro��ka'),
(515, 'Ostror�g'),
(516, 'Ostrowiec �wi�tokrzyski'),
(517, 'Ostr�da'),
(518, 'Ostr�w Lubelski'),
(519, 'Ostr�w Mazowiecka'),
(520, 'Ostr�w Wielkopolski'),
(521, 'Ostrzesz�w'),
(522, 'O�no Lubuskie'),
(523, 'O�wi�cim'),
(524, 'Otmuch�w'),
(525, 'Otwock'),
(526, 'Ozimek'),
(527, 'Ozork�w'),
(528, 'O�ar�w'),
(529, 'O�ar�w Mazowiecki'),
(530, 'Pabianice'),
(531, 'Paczk�w'),
(532, 'Paj�czno'),
(533, 'Pako��'),
(534, 'Parczew'),
(535, 'Pas��k'),
(536, 'Pasym'),
(537, 'Pelplin'),
(538, 'Pe�czyce'),
(539, 'Piaseczno'),
(540, 'Piaski'),
(541, 'Piast�w'),
(542, 'Piechowice'),
(543, 'Piekary �l�skie'),
(544, 'Pieni�no'),
(545, 'Pie�sk'),
(546, 'Pieszyce'),
(547, 'Pilawa'),
(548, 'Pilica'),
(549, 'Pilzno'),
(550, 'Pi�a'),
(551, 'Pi�awa G�rna'),
(552, 'Pi�cz�w'),
(553, 'Pionki'),
(554, 'Piotrk�w Kujawski'),
(555, 'Piotrk�w Trybunalski'),
(556, 'Pisz'),
(557, 'Piwniczna-Zdr�j'),
(558, 'Pleszew'),
(559, 'P�ock'),
(560, 'P�o�sk'),
(561, 'P�oty'),
(562, 'Pniewy'),
(563, 'Pobiedziska'),
(564, 'Podd�bice'),
(565, 'PodkowaLe�na'),
(566, 'Pogorzela'),
(567, 'Polanica-Zdr�j'),
(568, 'Polan�w'),
(569, 'Police'),
(570, 'Polkowice'),
(571, 'Po�aniec'),
(572, 'Po�czyn-Zdr�j'),
(573, 'Poniatowa'),
(574, 'Poniec'),
(575, 'Por�ba'),
(576, 'Pozna�'),
(577, 'Prabuty'),
(578, 'Praszka'),
(579, 'Prochowice'),
(580, 'Proszowice'),
(581, 'Pr�szk�w'),
(582, 'Pruchnik'),
(583, 'Prudnik'),
(584, 'Prusice'),
(585, 'Pruszcz Gda�ski'),
(586, 'Pruszk�w'),
(587, 'Przasnysz'),
(588, 'Przec�aw'),
(589, 'Przedb�rz'),
(590, 'Przedecz'),
(591, 'Przemk�w'),
(592, 'Przemy�l'),
(593, 'Przeworsk'),
(594, 'Przysucha'),
(595, 'Pszczyna'),
(596, 'Psz�w'),
(597, 'Puck'),
(598, 'Pu�awy'),
(599, 'Pu�tusk'),
(600, 'Puszczykowo'),
(601, 'Pyrzyce'),
(602, 'Pyskowice'),
(603, 'Pyzdry'),
(604, 'Rabka-Zdr�j'),
(605, 'Raci��'),
(606, 'Racib�rz'),
(607, 'Radk�w'),
(608, 'Radlin'),
(609, 'Rad��w'),
(610, 'Radom'),
(611, 'Radomsko'),
(612, 'Radomy�l Wielki'),
(613, 'Radymno'),
(614, 'Radziej�w'),
(615, 'Radzionk�w'),
(616, 'Radzymin'),
(617, 'Radzy� Che�mi�ski'),
(618, 'Radzy� Podlaski'),
(619, 'Rajgr�d'),
(620, 'Rakoniewice'),
(621, 'Raszk�w'),
(622, 'Rawa Mazowiecka'),
(623, 'Rawicz'),
(624, 'Recz'),
(625, 'Reda'),
(626, 'Rejowiec Fabryczny'),
(627, 'Resko'),
(628, 'Reszel'),
(629, 'Rogo�no'),
(630, 'Ropczyce'),
(631, 'R�an'),
(632, 'Ruciane-Nida'),
(633, 'Ruda �l�ska'),
(634, 'Rudnik nad Sanem'),
(635, 'Rumia'),
(636, 'Rybnik'),
(637, 'Rychwa�'),
(638, 'Rydu�towy'),
(639, 'Rydzyna'),
(640, 'Ryglice'),
(641, 'Ryki'),
(642, 'Ryman�w'),
(643, 'Ryn'),
(644, 'Rypin'),
(645, 'Rzepin'),
(646, 'Rzesz�w'),
(647, 'Rzg�w'),
(648, 'Sandomierz'),
(649, 'Sanok'),
(650, 'Sejny'),
(651, 'Serock'),
(652, 'S�dzisz�w'),
(653, 'S�dzisz�w Ma�opolski'),
(654, 'S�popol'),
(655, 'S�p�lno Kraje�skie'),
(656, 'Sian�w'),
(657, 'Siechnice'),
(658, 'Siedlce'),
(659, 'Siemianowice �l�skie'),
(660, 'Siemiatycze'),
(661, 'Sieniawa'),
(662, 'Sieradz'),
(663, 'Sierak�w'),
(664, 'Sierpc'),
(665, 'Siewierz'),
(666, 'Skalbmierz'),
(667, 'Ska�a'),
(668, 'Skarszewy'),
(669, 'Skaryszew'),
(670, 'Skar�ysko-Kamienna'),
(671, 'Skawina'),
(672, 'Sk�pe'),
(673, 'Skierniewice'),
(674, 'Skocz�w'),
(675, 'Skoki'),
(676, 'Sk�rcz'),
(677, 'Skwierzyna'),
(678, 'S�awa'),
(679, 'S�awk�w'),
(680, 'S�awno'),
(681, 'S�omniki'),
(682, 'S�ubice'),
(683, 'S�upca'),
(684, 'S�upsk'),
(685, 'Sob�tka'),
(686, 'Sochaczew'),
(687, 'Soko��w Ma�opolski'),
(688, 'Soko��w Podlaski'),
(689, 'Sok�ka'),
(690, 'Solec Kujawski'),
(691, 'Sompolno'),
(692, 'Sopot'),
(693, 'Sosnowiec'),
(694, 'So�nicowice'),
(695, 'Stalowa Wola'),
(696, 'Starachowice'),
(697, 'Stargard Szczeci�ski'),
(698, 'Starogard Gda�ski'),
(699, 'StaryS�cz'),
(700, 'Stasz�w'),
(701, 'Stawiski'),
(702, 'Stawiszyn'),
(703, 'St�pork�w'),
(704, 'Stepnica'),
(705, 'St�szew'),
(706, 'Stoczek �ukowski'),
(707, 'Stopnica'),
(708, 'Stronie �l�skie'),
(709, 'Strumie�'),
(710, 'Stryk�w'),
(711, 'Strzegom'),
(712, 'Strzelce Kraje�skie'),
(713, 'Strzelce Opolskie'),
(714, 'Strzelin'),
(715, 'Strzelno'),
(716, 'Strzy��w'),
(717, 'Sucha Beskidzka'),
(718, 'Sucha�'),
(719, 'Suchedni�w'),
(720, 'Suchowola'),
(721, 'Sulech�w'),
(722, 'Sulej�w'),
(723, 'Sulej�wek'),
(724, 'Sul�cin'),
(725, 'Sulmierzyce'),
(726, 'Su�kowice'),
(727, 'Supra�l'),
(728, 'Sura�'),
(729, 'Susz'),
(730, 'Suwa�ki'),
(731, 'Swarz�dz'),
(732, 'Syc�w'),
(733, 'Szadek'),
(734, 'Szamocin'),
(735, 'Szamotu�y'),
(736, 'Szczawnica'),
(737, 'Szczawno-Zdr�j'),
(738, 'Szczebrzeszyn'),
(739, 'Szczecin'),
(740, 'Szczecinek'),
(741, 'Szczekociny'),
(742, 'Szczucin'),
(743, 'Szczuczyn'),
(744, 'Szczyrk'),
(745, 'Szczytna'),
(746, 'Szczytno'),
(747, 'Szepietowo'),
(748, 'Szklarska Por�ba'),
(749, 'Szlichtyngowa'),
(750, 'Szprotawa'),
(751, 'Sztum'),
(752, 'Szubin'),
(753, 'Szyd�owiec'),
(754, '�cinawa'),
(755, '�lesin'),
(756, '�migiel'),
(757, '�rem'),
(758, '�roda �l�ska'),
(759, '�rodaWielkopolska'),
(760, '�wi�tniki G�rne'),
(761, '�widnica'),
(762, '�widnik'),
(763, '�widwin'),
(764, '�wiebodzice'),
(765, '�wiebodzin'),
(766, '�wiecie'),
(767, '�wierad�w-Zdr�j'),
(768, '�wierzawa'),
(769, '�wi�toch�owice'),
(770, '�winouj�cie'),
(771, 'Tarczyn'),
(772, 'Tarnobrzeg'),
(773, 'Tarnogr�d'),
(774, 'Tarnowskie G�ry'),
(775, 'Tarn�w'),
(776, 'Tczew'),
(777, 'Terespol'),
(778, 'T�uszcz'),
(779, 'Tolkmicko'),
(780, 'Tomasz�w Lubelski'),
(781, 'Tomasz�w Mazowiecki'),
(782, 'Toru�'),
(783, 'Torzym'),
(784, 'Toszek'),
(785, 'Trzcianka'),
(786, 'Trzciel'),
(787, 'Trzci�sko-Zdr�j'),
(788, 'Trzebiat�w'),
(789, 'Trzebinia'),
(790, 'Trzebnica'),
(791, 'Trzemeszno'),
(792, 'Tuchola'),
(793, 'Tuch�w'),
(794, 'Tuczno'),
(795, 'Tuliszk�w'),
(796, 'Turek'),
(797, 'Tuszyn'),
(798, 'Twardog�ra'),
(799, 'Tychowo'),
(800, 'Tychy'),
(801, 'Tyczyn'),
(802, 'Tykocin'),
(803, 'Tyszowce'),
(804, 'Ujazd'),
(805, 'Uj�cie'),
(806, 'Ulan�w'),
(807, 'Uniej�w'),
(808, 'Ustka'),
(809, 'Ustro�'),
(810, 'Ustrzyki Dolne'),
(811, 'Wadowice'),
(812, 'Wa�brzych'),
(813, 'Wa�cz'),
(814, 'Warka'),
(815, 'Warszawa'),
(816, 'Warta'),
(817, 'Wasilk�w'),
(818, 'W�brze�no'),
(819, 'W�chock'),
(820, 'W�growiec'),
(821, 'W�sosz'),
(822, 'Wejherowo'),
(823, 'W�gliniec'),
(824, 'W�gorzewo'),
(825, 'W�gorzyno'),
(826, 'W�gr�w'),
(827, 'Wi�z�w'),
(828, 'Wiele�'),
(829, 'Wielichowo'),
(830, 'Wieliczka'),
(831, 'Wielu�'),
(832, 'Wierusz�w'),
(833, 'Wi�cbork'),
(834, 'Wilamowice'),
(835, 'Wis�a'),
(836, 'Witkowo'),
(837, 'Witnica'),
(838, 'Wle�'),
(839, 'W�adys�awowo'),
(840, 'W�oc�awek'),
(841, 'W�odawa'),
(842, 'W�oszczowa'),
(843, 'Wodzis�aw �l�ski'),
(844, 'Wojciesz�w'),
(845, 'Wojkowice'),
(846, 'Wojnicz'),
(847, 'Wolb�rz'),
(848, 'Wolbrom'),
(849, 'Wolin'),
(850, 'Wolsztyn'),
(851, 'Wo�czyn'),
(852, 'Wo�omin'),
(853, 'Wo��w'),
(854, 'Wo�niki'),
(855, 'Wroc�aw'),
(856, 'Wronki'),
(857, 'Wrze�nia'),
(858, 'Wschowa'),
(859, 'Wyrzysk'),
(860, 'Wysoka'),
(861, 'Wysokie Mazowieckie'),
(862, 'Wyszk�w'),
(863, 'Wyszogr�d'),
(864, 'Wy�mierzyce'),
(865, 'Zab�ud�w'),
(866, 'Zabrze'),
(867, 'Zag�r�w'),
(868, 'Zag�rz'),
(869, 'Zakliczyn'),
(870, 'Zaklik�w'),
(871, 'Zakopane'),
(872, 'Zakroczym'),
(873, 'Zalewo'),
(874, 'Zambr�w'),
(875, 'Zamo��'),
(876, 'Zator'),
(877, 'Zawadzkie'),
(878, 'Zawichost'),
(879, 'Zawid�w'),
(880, 'Zawiercie'),
(881, 'Z�bki'),
(882, 'Z�bkowice �l�skie'),
(883, 'Zb�szynek'),
(884, 'Zb�szy�'),
(885, 'Zduny'),
(886, 'Zdu�ska Wola'),
(887, 'Zdzieszowice'),
(888, 'Zel�w'),
(889, 'Zgierz'),
(890, 'Zgorzelec'),
(891, 'Zielona G�ra'),
(892, 'Zielonka'),
(893, 'Zi�bice'),
(894, 'Z�ocieniec'),
(895, 'Z�oczew'),
(896, 'Z�otoryja'),
(897, 'Z�ot�w'),
(898, 'Z�oty Stok'),
(899, 'Zwierzyniec'),
(900, 'Zwole�'),
(901, '�abno'),
(902, '�aga�'),
(903, '�arki'),
(904, '�ar�w'),
(905, '�ary'),
(906, '�elech�w'),
(907, '�erk�w'),
(908, '�migr�d'),
(909, '�nin'),
(910, '�ory'),
(911, '�ukowo'),
(912, '�uromin'),
(913, '�ychlin'),
(914, '�yrard�w'),
(915, '�ywiec');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rezerwacje`
--

CREATE TABLE IF NOT EXISTS `rezerwacje` (
  `id_uzytkownika` int(10) unsigned NOT NULL,
  `id_wycieczki` int(10) unsigned NOT NULL,
  `czy_zaakceptowana` tinyint(1) DEFAULT NULL,
  `data_zlozenia` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_uzytkownika`,`id_wycieczki`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `rezerwacje`
--

INSERT INTO `rezerwacje` (`id_uzytkownika`, `id_wycieczki`, `czy_zaakceptowana`, `data_zlozenia`) VALUES
(3, 5, null, '2015-05-23 19:35:19'),
(2, 5, null, '2015-05-23 19:35:19'),
(4, 5, 1, '2015-05-23 19:35:19'),
(1, 4, null, '2015-05-23 19:35:19'),
(15, 4, 0, '2015-05-23 19:35:19'),
(20, 1, null, '2015-05-23 19:35:19'),
(19, 1, null, '2015-05-23 19:35:19'),
(4, 8, 1, '2015-05-23 19:35:19'),
(5, 8, 0, '2015-05-23 19:35:19'),
(10, 8, null, '2015-05-23 19:35:19');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uczestnicy`
--

CREATE TABLE IF NOT EXISTS `uczestnicy` (
  `id_uzytkownika` int(10) unsigned NOT NULL,
  `id_wycieczki` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_uzytkownika`,`id_wycieczki`),
  KEY `id_wycieczki` (`id_wycieczki`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `uczestnicy`
--

INSERT INTO `uczestnicy` (`id_uzytkownika`, `id_wycieczki`) VALUES
(1, 2),
(1, 8),
(2, 5),
(2, 8),
(3, 6),
(3, 7),
(3, 8),
(5, 7),
(6, 3),
(8, 3),
(8, 4),
(9, 4),
(9, 7),
(10, 4),
(10, 7),
(11, 3),
(12, 5),
(14, 6),
(14, 7),
(15, 3),
(16, 3),
(17, 5),
(18, 5),
(19, 5),
(20, 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownicy`
--

CREATE TABLE IF NOT EXISTS `uzytkownicy` (
  `id_uzytkownika` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(30) COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(255) COLLATE utf8_polish_ci NOT NULL,
  `imie` varchar(20) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(30) COLLATE utf8_polish_ci NOT NULL,
  `plec` enum('m�czyzna','kobieta') COLLATE utf8_polish_ci DEFAULT NULL,
  `id_miasta` int(10) unsigned DEFAULT NULL,
  `moderator` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_uzytkownika`),
  UNIQUE KEY `email` (`email`),
  KEY `id_miasta` (`id_miasta`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=21 ;

--
-- Zrzut danych tabeli `uzytkownicy`
--

INSERT INTO `uzytkownicy` (`id_uzytkownika`, `email`, `haslo`, `imie`, `nazwisko`, `plec`, `id_miasta`, `moderator`) VALUES
(1, 'jan@gmail.com', 'poop', 'Jan', 'Janicki', 'm�czyzna', 1, 1),
(2, 'kwiatuszek33@wp.pl', 'poop', 'Janina', 'Kowalczyk', 'kobieta', NULL, 0),
(3, 'buzz@we.wi.wes.com', 'poop', 'Joachim', 'Brz�yszczykiewicz', 'm�czyzna', 915, 0),
(4, 'genderfluid@lgbt.com', 'poop', 'Alex', 'Michlewicz', NULL, 275, 0),
(5, 'really@lost.gov.au', 'poop', 'Karolina', 'Ba�troczyk', 'kobieta', 100, 1),
(6, 'komik@zkp.com', 'poop', 'Karolina', 'Ba�troczyk', 'kobieta', 200, 0),
(7, 'jan2@gmail.com', 'poop', 'Emil', 'Kwiatkowski', 'm�czyzna', 201, 0),
(8, 'emilia@lost.com', 'nie-poop', 'Emilia', 'Malinowska', 'kobieta', 300, 0),
(9, 'lost@gmail.com', 'tez_nie_poop', 'Hieronim', 'Bosch', 'm�czyzna', NULL, 0),
(10, 'dwuznaczny@wp.pl', 'poop', 'Maria', 'Radek', NULL, NULL, 0),
(11, 'boom@chakalaka.nz', 'sheep', 'Bernard', 'Chrystusiewicz', 'm�czyzna', 18, 0),
(12, 'ma@spam-nest.porn', 'spam', 'Alicja', 'Kot', 'kobieta', 29, 0),
(13, 'pies@mo.ps', 'pies', 'Alex', 'Moczygemba', NULL, 10, 0),
(14, 'wololo@priest.ee', 'pies', 'Karol', 'Wojty�a', 'm�czyzna', 815, 0),
(15, 'holier@than.tho.us', 'swiety', 'Joanna', 'Papie�yca', 'kobieta', 815, 1),
(16, 'wer@gmail,com', 'nie-poop', 'Weronika', 'W�jcik', 'kobieta', 815, 0),
(17, 'woops@gmail.com', 'poop', 'Hamlet', 'Odynson', 'm�czyzna', NULL, 0),
(18, 'plus_dwa@studio-rea.pl', 'tez_nie_poop_%%%%3', 'Genowefa', 'Raciborska', 'kobieta', 26, 0),
(19, 'a123@generic.co.uk', '1234567890', 'Jan', 'Nowak', 'm�czyzna', 855, 0),
(20, 'dwudziestnik@twentysided.com', 'bollocks', 'Shamus', 'Young', 'm�czyzna', 163, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `wycieczki`
--

CREATE TABLE IF NOT EXISTS `wycieczki` (
  `id_wycieczki` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `liczba_miejsc` int(11) NOT NULL,
  `nazwa` varchar(30) COLLATE utf8_polish_ci NOT NULL,
  `opis` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `dlugosc_trasy` int(11) NOT NULL,
  `poziom_trudnosci` enum('�atwy','�redni','trudny') COLLATE utf8_polish_ci NOT NULL,
  `lokalizacja` varchar(30) COLLATE utf8_polish_ci NOT NULL,
  `data_poczatku` date NOT NULL,
  `data_konca` date NOT NULL,
  `cena` decimal(8,2) NOT NULL,
  `id_moderatora` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_wycieczki`),
  KEY `wycieczki_uzytkownicy_id_foreign` (`id_moderatora`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci AUTO_INCREMENT=9 ;

--
-- Zrzut danych tabeli `wycieczki`
--

INSERT INTO `wycieczki` (`id_wycieczki`, `liczba_miejsc`, `nazwa`, `opis`, `dlugosc_trasy`, `poziom_trudnosci`, `lokalizacja`, `data_poczatku`, `data_konca`, `cena`, `id_moderatora`) VALUES
(1, 10, 'Wycieczka do pustek', 'Pusta wycieczka.', 102, '�atwy', 'Pustki Pr�ne', '2015-04-08', '2015-04-23', '3.00', 1),
(2, 1, 'Bez opisu', NULL, 20, 'trudny', 'Szszszszsz....', '2015-04-22', '2015-04-22', '100.99', 1),
(3, 5, 'Pe�na', 'Wycieczka z zape�nionymi miejscami', 99, '�redni', 'gdzie�tam', '2015-04-30', '2015-05-04', '99.99', 15),
(4, 4, 'LF1more', 'Wycieczka z zape�nionymi wszystkimi miejscami opr�cz ostatniego.', 30, '�atwy', 'Blisko', '2015-04-22', '2015-04-23', '459.39', 5),
(5, 200, 'Gigant', 'Na przysz�o��, do testowania obci��e. Poza tym: Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut fringilla enim id augue ultrices molestie. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed faucibus tempus libero sit amet pretium. Al', 500, 'trudny', 'Wsz�dzie, wsz�dzie, WSZ�DZIE', '2015-04-23', '2015-08-26', '4563.39', 5),
(6, 200, 'Duplikat', 'Dwie identyczne.', 500, '�redni', 'Daleko', '2015-04-23', '2015-05-05', '1563.39', 1),
(7, 200, 'Duplikat', 'Dwie identyczne.', 500, '�redni', 'Daleko', '2015-04-23', '2015-05-05', '1563.39', 1),
(8, 20, 'Stara, w dalekiej przesz�o�ci', 'Reprezentuje wycieczk� przesz��.', 33, '�atwy', 'Przesz�o��', '2014-02-17', '2014-03-18', '0.35', 1);
