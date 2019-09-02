

--
-- Table structure for table `app_verify`
--

CREATE TABLE IF NOT EXISTS `app_verify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyer` varchar(255) NOT NULL,
  `purchase_code` varchar(255) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `app_verify`
--

INSERT INTO `app_verify` (`id`, `buyer`, `purchase_code`, `status`) VALUES
(1, '', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_admin`
--

CREATE TABLE IF NOT EXISTS `tbl_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tbl_admin`
--

INSERT INTO `tbl_admin` (`id`, `username`, `password`, `email`) VALUES
(1, 'admin', 'adminq1w2', 'viaviwebtech@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_category`
--

CREATE TABLE IF NOT EXISTS `tbl_category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `category_image` varchar(255) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tbl_category`
--

INSERT INTO `tbl_category` (`cid`, `category_name`, `category_image`) VALUES
(1, 'Sports Channel1', '92716_94282_fashionchannels.png'),
(2, 'Fashion Channel', '94282_fashionchannels.png'),
(3, 'Entertainment Channel', '79517_entertainmentchannel.png'),
(4, 'News Channel', '14243_news.png');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_channels`
--

CREATE TABLE IF NOT EXISTS `tbl_channels` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cat_id` int(11) NOT NULL,
  `channel_title` varchar(100) NOT NULL,
  `channel_url` varchar(255) NOT NULL,
  `channel_thumbnail` varchar(255) NOT NULL,
  `channel_desc` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `tbl_channels`
--

INSERT INTO `tbl_channels` (`id`, `cat_id`, `channel_title`, `channel_url`, `channel_thumbnail`, `channel_desc`) VALUES
(1, 1, 'Ten Sports', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '50289_IndiaSportsTV.png', 'Ten Sports Live - Watch live tournaments, matches, events of your favorite sports online.'),
(2, 1, 'Sony Six', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '94013_SonySix.png', 'Watch Sony Six Live Streaming,Sony Six Tv Live.\r\n\r\nLorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and s'),
(3, 2, 'f.Diamond', 'http://edgeb.streaming.sk/fashiontv/fashiontv.stream/playlist_b125000_w304569164.m3u8', '34851_fdai.jpg', 'f.Diamond - International network dedicated to fashion news, shows, models and designers broadcasting 24 hours a day.'),
(4, 2, 'Fashion TV', 'http://edgeb.streaming.sk/fashiontv/fashiontv.stream/playlist_b125000_w304569164.m3u8', '81756_fash.jpg', 'FashionTV Live. Stay tuned for your favorite FashionTV programs and all the latest from the worlds major fashion and celebrity events.'),
(5, 4, 'NewsX', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '24392_NewsX.jpg', 'NewsX - Best English News Channel of the year 2014. Get the latest IT news, latest breaking news, current affairs, politics, business, technology etc..'),
(6, 4, 'Times Now', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '16393_timees.png', 'TIMES NOW is a Leading 24-hour English News channel that provides complete live coverage of international news, business news, breaking news..'),
(8, 3, 'MTV', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '88773_mtv.png', 'MTV gives you the hottest buzz from the entertainment world that will keep you hooked! Be the first to catch the latest MTV shows, music, artists and more!'),
(9, 3, 'Star TV', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '27976_star_tv.jpg', 'Star TV is all about News, Entertainment, Lifestyle and  Cooking shows...'),
(10, 3, 'TRT HABER', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '19501_trthaber.png', 'TRT new face in the world of the Internet, international and local news, domestic news, foreign news...'),
(11, 3, 'TV8', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '58533_tv8.jpg', 'TV8 offers over 100 television channels, a comprehensive program. TV8, watch high quality TV, HD quality watch TV 8. '),
(12, 3, '9XM', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '37336_9xm.png', 'Bollywood Music at its best, thats what 9XM is all about.'),
(13, 1, 'ESPN', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '44261_espn.png', 'Get the latest NBA basketball news, scores, stats, standings, fantasy games, and more from ESPN'),
(14, 1, 'Star Sports', 'http://bglive-a.bitgravity.com/ndtv/prolo/live/native', '47402_starsports1.jpg', 'Watch Star Sports Live Streaming.');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_review`
--

CREATE TABLE IF NOT EXISTS `tbl_review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_id` varchar(255) NOT NULL,
  `review` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `tbl_review`
--

INSERT INTO `tbl_review` (`id`, `channel_id`, `review`) VALUES
(5, '1', 'hello this is a temp meg'),
(12, '2', 'ggg'),
(13, '11', 'no wotk');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_version`
--

CREATE TABLE IF NOT EXISTS `tbl_version` (
  `vid` int(11) NOT NULL AUTO_INCREMENT,
  `version_code` varchar(255) NOT NULL,
  `version_messages` varchar(255) NOT NULL,
  `version_url` varchar(255) NOT NULL,
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tbl_version`
--

INSERT INTO `tbl_version` (`vid`, `version_code`, `version_messages`, `version_url`) VALUES
(1, '2', 'New Update Available please download it.', 'https://www.google.co.in/');

