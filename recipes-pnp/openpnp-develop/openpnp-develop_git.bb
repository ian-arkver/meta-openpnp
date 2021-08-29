SUMMARY = "OpenPnP - Open Pick and Place"
DESCRIPTION = "Build OpenPnP from git develop branch"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "git://github.com/openpnp/openpnp.git;protocol=https;branch=${SRC_BRANCH} \
"

SRC_BRANCH = "develop"
SRCREV = "${AUTOREV}"
PVBASE := "${PV}"
PV = "${PVBASE}.${SRCPV}"

S = "${WORKDIR}/git"

do_compile() {
	mvn -DskipTests package
}

do_install() {
	install -d "${D}${libdir}/openpnp"
	install -d "${D}${libdir}/openpnp/target"
	install -d "${D}${libdir}/openpnp/target/lib"
	install -m 0755 "${S}"/openpnp.sh "${D}${libdir}/openpnp/"
	install -m 0644 "${S}"/target/*.jar "${D}${libdir}/openpnp/target/"
	install -m 0644 "${S}"/target/lib/* "${D}${libdir}/openpnp/target/lib/"
	install -d "${D}${bindir}"
	ln -s ${libdir}/openpnp/openpnp.sh ${D}${bindir}/openpnp
}

FILES:${PN} = "${libdir} ${bindir}"
